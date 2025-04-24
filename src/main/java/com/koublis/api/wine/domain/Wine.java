package com.koublis.api.wine.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "wines")
@With
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Wine {

    @Id
    @UuidGenerator
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "BINARY(16)")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "cave_id", referencedColumnName = "id", nullable = false)
    private Cave cave;

    @Column(name = "appellation", length = 50)
    private String appellation;

    @Column(name = "color", length = 50)
    private String color;

    @Column(name = "regions")
    private String regions;

    @Column(name = "country", length = 50)
    private String country;

    @Column(name = "classification", length = 50)
    private String classification;

    @Column(name = "vintage")
    private Integer vintage;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "is_primeur")
    private Boolean isPrimeur;

}
