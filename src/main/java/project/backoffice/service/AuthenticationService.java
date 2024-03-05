package project.backoffice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.backoffice.auth.AuthenticationRequest;
import project.backoffice.auth.RegisterRequest;
import project.backoffice.dto.UserAuthDTO;
import project.backoffice.entity.Quality;
import project.backoffice.exception.ApiException;
import project.backoffice.exception.MessageExceptionEnum;
import project.backoffice.entity.Role;
import project.backoffice.entity.User;
import project.backoffice.mapper.UserMapper;
import project.backoffice.repository.QualityRepository;
import project.backoffice.repository.UserRepository;

import java.sql.Date;
import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final QualityRepository qualityRepository;
    private final UserMapper userMapper;

    public UserAuthDTO register(RegisterRequest request) throws ApiException {
        if(userRepository.findByEmail(request.getEmail()).isPresent()){
            throw new ApiException(HttpStatus.BAD_REQUEST, String.format(MessageExceptionEnum.USER_ALREADY_EXISTS.getMessage(), request.getEmail()));
        }

        checkRegisterFields(request);

        var user= User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .phone(request.getPhone())
                .isActive(true)
                .libraryTimestamp(Date.from(Instant.now()))
                .token("temp-" + UUID.randomUUID().toString())
                .quality(request.getQuality())
                .timestamp(Date.from(Instant.now()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        user.setToken(jwtToken);
        userRepository.save(user);
        return userMapper.toAuthDTO(user);
    }

    public UserAuthDTO authenticate(AuthenticationRequest request) {
        checkLoginFields(request);
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        if(user.getRole() != Role.ADMIN) {
            throw new ApiException(HttpStatus.FORBIDDEN, MessageExceptionEnum.ACCESS_DENIED.getMessage());
        }
        if(!user.isActive()) {
            throw new ApiException(HttpStatus.FORBIDDEN, MessageExceptionEnum.USER_DISABLED.getMessage());
        }
        var jwtToken = jwtService.generateToken(user);
        user.setToken(jwtToken);
        userRepository.save(user);
        return userMapper.toAuthDTO(user);
    }

    private void checkRegisterFields(RegisterRequest request) {
        if (request.getFirstName() == null || request.getFirstName().isEmpty()) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "First name is required");
        }
        if (request.getLastName() == null || request.getLastName().isEmpty()) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Last name is required");
        }
        if (request.getEmail() == null || request.getEmail().isEmpty()) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Email is required");
        }
        if (request.getPassword() == null || request.getPassword().isEmpty()) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Password is required");
        }
        if (request.getPhone() == null || request.getPhone().isEmpty()) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Phone is required");
        }
        if (request.getQuality() == null || request.getQuality().getId() == 0) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Quality is required");
        } else if (request.getQuality().getId() > 0) {
            Quality quality = qualityRepository.findById(request.getQuality().getId()).orElseThrow(
                    () -> new ApiException(HttpStatus.NOT_FOUND, "Quality not found for id: " + request.getQuality().getId()));
            request.setQuality(quality);
        }
    }

    private void checkLoginFields(AuthenticationRequest request) {
        if ((request.getEmail() == null || request.getEmail().isEmpty()) || (request.getPassword() == null || request.getPassword().isEmpty())) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Login or password incorrect");
        }
    }
}