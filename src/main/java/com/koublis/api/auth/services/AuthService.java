package com.koublis.api.auth.services;

import com.koublis.api.auth.controllers.dto.requests.LoginRequest;
import com.koublis.api.auth.controllers.dto.requests.SignupRequest;
import com.koublis.api.auth.controllers.dto.responses.JwtResponse;
import com.koublis.api.auth.controllers.dto.responses.MessageResponse;
import com.koublis.api.auth.domain.ERole;
import com.koublis.api.auth.domain.Role;
import com.koublis.api.auth.domain.User;
import com.koublis.api.auth.domain.UserDetailsImpl;
import com.koublis.api.auth.repositories.RoleRepository;
import com.koublis.api.auth.repositories.UserRepository;
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

import java.util.HashSet;

import static java.util.Objects.isNull;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;

    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

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
                .toList();

        return ResponseEntity.ok(JwtResponse.builder()
                .token(jwt)
                .id(userDetails.getId())
                .username(userDetails.getUsername())
                .email(userDetails.getEmail())
                .remember(loginRequest.getRemember())
                .roles(roles)
                .build());
    }

    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(
                            MessageResponse.builder()
                                    .message("Error: Username is already taken!")
                                    .build()
                    );
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(
                            MessageResponse.builder()
                                    .message("Error: Email is already in use!")
                                    .build()
                    );
        }

        val strRoles = signUpRequest.getRoles();
        val roles = new HashSet<Role>();

        if (isNull(strRoles)) {
            val userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin" -> {
                        val adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                    }
                    case "mod" -> {
                        val modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);
                    }
                    default -> {
                        val userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                    }
                }
            });
        }

        // Create new user's account
        val user = User.builder()
                .username(signUpRequest.getUsername())
                .email(signUpRequest.getEmail())
                .password(encoder.encode(signUpRequest.getPassword()))
                .roles(roles)
                .build();
        userRepository.save(user);

        return ResponseEntity.ok(
                MessageResponse.builder()
                        .message("User registered successfully!")
                        .build()
        );
    }
}
