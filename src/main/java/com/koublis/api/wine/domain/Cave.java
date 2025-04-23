package com.koublis.api.wine.domain;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import static jakarta.persistence.CascadeType.ALL;

@Entity
@With
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Cave implements Serializable {

    @OneToMany(cascade = ALL, mappedBy = "cave", fetch = FetchType.EAGER)
    private List<Wine> wines;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

}
