package com.koublis.model.documents;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Builder(toBuilder = true)
@Document(collection = "wines")
public class Wine implements Serializable {

    @DBRef
    private Cave cave; // Référence à la cave

    @Id
    private Long wineId;
    private String wine;
    private String wineSlug;
    private String appellation;
    private String appellationSlug;
    private String color;
    private String wineType;
    private List<String> regions;
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
