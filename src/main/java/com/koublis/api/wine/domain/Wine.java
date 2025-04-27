package com.koublis.api.wine.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

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
    @Column(name = "id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "cave_id", referencedColumnName = "id", nullable = false)
    private Cave cave;

    @Column(name = "count")
    private Integer count;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "vintage")
    private Integer vintage;

    @Column(name = "country", length = 50)
    private String country;

    @Column(name = "color", length = 50)
    private String color;

    @Column(name = "regions")
    private String regions;

    @Column(name = "classification", length = 50)
    private String classification;

    @Column(name = "primeur")
    private Boolean primeur;

}
