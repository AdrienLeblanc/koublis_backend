package com.koublis.api.auth.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.koublis.api.auth.controllers.dto.requests.LoginRequest;
import com.koublis.api.auth.controllers.dto.requests.SignupRequest;
import com.koublis.api.auth.controllers.dto.responses.JwtResponse;
import com.koublis.api.auth.controllers.dto.responses.MessageResponse;
import com.koublis.api.auth.domain.Role;
import com.koublis.api.auth.domain.User;
import com.koublis.api.auth.domain.UserDetailsImpl;
import com.koublis.api.auth.repositories.UserRepository;
import com.koublis.configuration.exceptions.Reason;
import com.koublis.configuration.exceptions.ResourceNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        val authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        val jwt = jwtService.generateJwtToken(authentication);

        val userDetails = (UserDetailsImpl) authentication.getPrincipal();
        val roles = userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse(null);

        return ResponseEntity.ok(JwtResponse.builder()
                .token(jwt)
                .id(userDetails.getId())
                .username(userDetails.getUsername())
                .email(userDetails.getEmail())
                .remember(loginRequest.getRemember())
                .role(roles)
                .build());
    }

    public ResponseEntity<MessageResponse> registerUser(@Valid @RequestBody SignupRequest signUpRequest) throws JsonProcessingException {
        if (Boolean.TRUE.equals(userRepository.existsByUsername(signUpRequest.getUsername()))) {
            return ResponseEntity
                    .badRequest()
                    .body(
                            MessageResponse.builder()
                                    .message("Error: Username is already taken!")
                                    .build()
                    );
        }

        if (Boolean.TRUE.equals(userRepository.existsByEmail(signUpRequest.getEmail()))) {
            return ResponseEntity
                    .badRequest()
                    .body(
                            MessageResponse.builder()
                                    .message("Error: Email is already in use!")
                                    .build()
                    );
        }

        // Create new user's account
        val user = User.builder()
                .username(signUpRequest.getUsername())
                .email(signUpRequest.getEmail())
                .password(encoder.encode(signUpRequest.getPassword()))
                .role(Role.valueOf(signUpRequest.getRole()))
                .build();

        log.debug("Saving user: {}", new ObjectMapper().writeValueAsString(user));
        userRepository.save(user);

        return ResponseEntity.ok(
                MessageResponse.builder()
                        .message("User registered successfully!")
                        .build()
        );
    }

    public ResponseEntity<MessageResponse> deleteUser(String username) {
        val user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException(Reason.USER_NOT_FOUND));

        userRepository.delete(user);

        return ResponseEntity.ok(
                MessageResponse.builder()
                        .message("User deleted successfully!")
                        .build()
        );
    }
}
