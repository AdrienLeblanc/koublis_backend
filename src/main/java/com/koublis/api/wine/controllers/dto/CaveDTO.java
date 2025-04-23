package com.koublis.api.wine.controllers.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
@Builder
public class CaveDTO {
    private UUID id;
    private String name;
    private List<WineDTO> wines;
}
