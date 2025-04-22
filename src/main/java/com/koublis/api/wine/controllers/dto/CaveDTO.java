package com.koublis.api.wine.controllers.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class CaveDTO {
    private Long id;
    private String name;
    private List<WineDTO> wines;
}
