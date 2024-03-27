package project.backoffice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;
import project.backoffice.auth.AuthenticationRequest;
import project.backoffice.auth.RegisterRequest;
import project.backoffice.dto.ResetPasswordDTO;
import project.backoffice.entity.User;
import project.backoffice.exception.ApiException;
import project.backoffice.exception.ApiExceptionHandler;
import project.backoffice.exception.MessageExceptionEnum;
import project.backoffice.repository.UserRepository;
import project.backoffice.dto.UserAuthDTO;
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
    public ResponseEntity<UserAuthDTO> register(@RequestBody RegisterRequest request){
        UserAuthDTO response = authenticationService.register(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/loginAdmin")
    @Operation(summary = "Authenticate admin",
    responses = {
        @ApiResponse(responseCode = "200", description = "Admin authenticated"),
        @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    public ResponseEntity<UserAuthDTO> authenticateAdmin(@RequestBody AuthenticationRequest request) {
        try {
            UserAuthDTO response = authenticationService.authenticate(request,true);
            return ResponseEntity.ok(response);
        } catch (BadCredentialsException e) {
            throw new ApiException(HttpStatus.UNAUTHORIZED, MessageExceptionEnum.LOGIN_OR_PASSWORD_INCORRECT);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<UserAuthDTO> authenticate(@RequestBody AuthenticationRequest request) {
        try {
            UserAuthDTO response = authenticationService.authenticate(request,false);
            return ResponseEntity.ok(response);
        } catch (BadCredentialsException e) {
            throw new ApiException(HttpStatus.UNAUTHORIZED, MessageExceptionEnum.LOGIN_OR_PASSWORD_INCORRECT);
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordDTO resetPasswordDTO) {
        if (resetPasswordDTO.getPassword() == null) {
            resetPasswordService.askResetPassword(resetPasswordDTO);
        } else {
            resetPasswordService.resetPassword(resetPasswordDTO);
        }

        return ResponseEntity.ok().build();
    }
}
