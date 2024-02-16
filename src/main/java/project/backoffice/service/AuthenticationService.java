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
import project.backoffice.exception.ApiException;
import project.backoffice.exception.MessageExceptionEnum;
import project.backoffice.entity.Role;
import project.backoffice.entity.User;
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

    public User register(RegisterRequest request) throws ApiException {
        if(userRepository.findByEmail(request.getEmail()).isPresent()){
            throw new ApiException(HttpStatus.BAD_REQUEST, MessageExceptionEnum.USER_ALREADY_EXISTS);
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
        return user;
    }

    public User authenticate(AuthenticationRequest request) {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
            var user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
            var jwtToken = jwtService.generateToken(user);
            user.setToken(jwtToken);
            userRepository.save(user);
            return user;
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
        }
    }
}