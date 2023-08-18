package vn.ping.ping_todo_api.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.ping.ping_todo_api.error.UserAlreadyExistException;
import vn.ping.ping_todo_api.models.request.AuthenticationRequest;
import vn.ping.ping_todo_api.models.request.RegisterRequest;
import vn.ping.ping_todo_api.services.IAuthenticationService;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final IAuthenticationService authenticationService;
    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody RegisterRequest request
    ){
        try {
            return ResponseEntity.ok(authenticationService.register(request));
        } catch (UserAlreadyExistException | IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> login(
            @RequestBody AuthenticationRequest request
    ){
        try {
            return ResponseEntity.ok(authenticationService.authenticate(request));
        } catch(UsernameNotFoundException unfEX) {
            return ResponseEntity.badRequest().body(unfEX.getMessage());
        }
    }
}
