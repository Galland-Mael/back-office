package project.backoffice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;
import project.backoffice.auth.AuthenticationRequest;
import project.backoffice.auth.AuthenticationResponse;
import project.backoffice.auth.RegisterRequest;
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
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest request) {
        try {
            AuthenticationResponse response = authenticationService.authenticate(request);
            return ResponseEntity.ok(response);
        } catch (BadCredentialsException e) {
            return apiExceptionHandler.handleApiException(new ApiException(HttpStatus.FORBIDDEN, MessageExceptionEnum.LOGIN_OR_PASSWORD_INCORRECT));
        } catch (Exception e) {
            return apiExceptionHandler.handleApiException(e);
        }
    }
}
