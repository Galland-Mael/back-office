package project.backoffice.auth;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;
import project.backoffice.exception.ApiException;
import project.backoffice.exception.ApiExceptionHandler;
import project.backoffice.exception.MessageExceptionEnum;

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
            try {
                AuthenticationResponse response = authenticationService.authenticate(request);
                return ResponseEntity.ok(response);
            } catch (BadCredentialsException e) {
                throw new ApiException(HttpStatus.FORBIDDEN, MessageExceptionEnum.LOGIN_OR_PASSWORD_INCORRECT);
            } catch (Exception e) {
                return apiExceptionHandler.handleApiException(e);
            }
        } catch (ApiException e) {
            return apiExceptionHandler.handleApiException(e);
        }

    }

}
