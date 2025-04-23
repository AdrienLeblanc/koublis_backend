package com.koublis.api.wine.domain;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@With
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Wine implements Serializable {

    @ManyToOne
    @JoinColumn(name = "caveId", referencedColumnName = "id", nullable = false)
    private Cave cave;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String wine;
    private String wineSlug;
    private String appellation;
    private String appellationSlug;
    private String color;
    private String wineType;
    private String regions;
    private String country;
    private String classification;
    private String vintage;
    private LocalDate date;
    private Boolean isPrimeurs;
    private Long score;
    private String confidenceIndex;
    private Long journalistCount;
    private Long lwin;
    private Long lwin11;

}
