package project.backoffice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;
import project.backoffice.auth.AuthenticationRequest;
import project.backoffice.auth.RegisterRequest;
import project.backoffice.entity.User;
import project.backoffice.exception.ApiException;
import project.backoffice.exception.ApiExceptionHandler;
import project.backoffice.exception.MessageExceptionEnum;
import project.backoffice.service.AuthenticationService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final ApiExceptionHandler apiExceptionHandler;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request){
        User response = authenticationService.register(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest request) {
        User response = authenticationService.authenticate(request);
        return ResponseEntity.ok(response);
    }
}
