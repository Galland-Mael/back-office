package project.backoffice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.backoffice.auth.AuthenticationRequest;
import project.backoffice.auth.AuthenticationResponse;
import project.backoffice.auth.RegisterRequest;
import project.backoffice.exception.ApiException;
import project.backoffice.exception.MessageExceptionEnum;
import project.backoffice.service.JwtService;
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

    public AuthenticationResponse register(RegisterRequest request) throws ApiException {
        if(userRepository.findByEmail(request.getEmail()).isPresent()){
            throw new ApiException(HttpStatus.BAD_REQUEST, MessageExceptionEnum.USER_ALREADY_EXISTS);
        }

        var user= User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .phone(request.getPhone())
                .facebookId(request.getFacebookId())
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
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
            var user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
            var jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
    }
}