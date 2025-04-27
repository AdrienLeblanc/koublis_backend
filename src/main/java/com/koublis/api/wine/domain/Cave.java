package com.koublis.api.wine.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

import static jakarta.persistence.CascadeType.ALL;

@Entity
@Table(name = "caves")
@With
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Cave {

    @Id
    @UuidGenerator
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @OneToMany(cascade = ALL, mappedBy = "cave", fetch = FetchType.EAGER)
    private List<Wine> wines;

}
