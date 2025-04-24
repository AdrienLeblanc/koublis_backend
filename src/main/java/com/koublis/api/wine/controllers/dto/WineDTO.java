package com.koublis.api.wine.controllers.dto;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
public class WineDTO {

    private UUID id;
    private String appellation;
    private String classification;
    private String color;
    private String country;
    private LocalDate date;
    private Boolean isPrimeur;
    private List<String> regions;
    private Integer vintage;

}
