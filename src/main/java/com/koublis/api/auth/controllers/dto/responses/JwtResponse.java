package com.koublis.api.auth.controllers.dto.responses;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;


@Getter
@Builder
public class JwtResponse {

    private final String type = "Bearer";
    private String token;
    private UUID id;
    private String username;
    private String email;
    private Boolean remember;
    private String role;

}