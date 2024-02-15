package project.backoffice.controller;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;
import project.backoffice.auth.AuthenticationRequest;
import project.backoffice.auth.AuthenticationResponse;
import project.backoffice.auth.RegisterRequest;
import project.backoffice.dto.ResetPasswordDTO;
import project.backoffice.entity.User;
import project.backoffice.exception.ApiException;
import project.backoffice.exception.ApiExceptionHandler;
import project.backoffice.exception.MessageExceptionEnum;
import project.backoffice.repository.UserRepository;
import project.backoffice.service.AuthenticationService;
import project.backoffice.service.ResetPasswordService;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final ResetPasswordService resetPasswordService;
    private final ApiExceptionHandler apiExceptionHandler;
    private final UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request){
        try {
            AuthenticationResponse response = authenticationService.register(request);
            return ResponseEntity.ok(response);
        } catch (ApiException e) {
            return apiExceptionHandler.handleApiException(e);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest request) {
        try {
            AuthenticationResponse response = authenticationService.authenticate(request);
            return ResponseEntity.ok(response);
        } catch (BadCredentialsException e) {
            throw new ApiException(HttpStatus.FORBIDDEN, MessageExceptionEnum.LOGIN_OR_PASSWORD_INCORRECT);
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordDTO resetPasswordDTO) {
        if (resetPasswordDTO.getPassword() == null) {
            resetPasswordService.askResetPassword(resetPasswordDTO);
            return ResponseEntity.ok().build();
        } else {

            if(resetPasswordDTO.getToken() == null) {
                throw new ApiException(HttpStatus.BAD_REQUEST, MessageExceptionEnum.RESET_PASSWORD_TOKEN_INVALID);
            }

            User user = userRepository.findByEmail(resetPasswordDTO.getEmail())
                    .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, MessageExceptionEnum.USER_NOT_FOUND));

            if(StringUtils.equals(user.getToken(), resetPasswordDTO.getToken())) {
                resetPasswordService.resetPassword(resetPasswordDTO);
                return ResponseEntity.ok().build();
            } else {
                throw new ApiException(HttpStatus.FORBIDDEN, MessageExceptionEnum.RESET_PASSWORD_TOKEN_INVALID);
            }
        }
    }
}
