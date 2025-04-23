package com.koublis.api.wine.controllers.dto;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
public class WineDTO implements Serializable {

    private UUID id;
    private String appellation;
    private String appellationSlug;
    private String classification;
    private String color;
    private String confidenceIndex;
    private String country;
    private LocalDate date;
    private Boolean isPrimeurs;
    private Long journalistCount;
    private Long lwin;
    private Long lwin11;
    private List<String> regions;
    private Long score;
    private String vintage;
    private String wine;
    private String wineSlug;
    private String wineType;

}
