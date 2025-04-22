package com.koublis.api.auth.controllers.dto.responses;

import lombok.Builder;
import lombok.Getter;

import java.util.List;


@Getter
@Builder
public class JwtResponse {

    private final String type = "Bearer";
    private String token;
    private String id;
    private String username;
    private String email;
    private Boolean remember;
    private List<String> roles;

}