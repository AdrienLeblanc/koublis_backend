package com.koublis.api.auth.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.koublis.api.auth.controllers.dto.requests.LoginRequest;
import com.koublis.api.auth.controllers.dto.requests.SignupRequest;
import com.koublis.api.auth.controllers.dto.responses.JwtResponse;
import com.koublis.api.auth.controllers.dto.responses.MessageResponse;
import com.koublis.api.auth.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-in")
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return authService.authenticateUser(loginRequest);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<MessageResponse> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        return authService.registerUser(signUpRequest);
    }

    @DeleteMapping
    public ResponseEntity<MessageResponse> deleteUser(@RequestParam String username) {
        return authService.deleteUser(username);
    }
}
