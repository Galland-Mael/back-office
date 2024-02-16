package project.backoffice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.backoffice.auth.AuthenticationRequest;
import project.backoffice.auth.RegisterRequest;
import project.backoffice.dto.UserAuthDTO;
import project.backoffice.service.AuthenticationService;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<UserAuthDTO> register(@RequestBody RegisterRequest request){
        UserAuthDTO response = authenticationService.register(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<UserAuthDTO> authenticate(@RequestBody AuthenticationRequest request) {
        UserAuthDTO response = authenticationService.authenticate(request);
        return ResponseEntity.ok(response);
    }
}
