package com.koublis.domain.mongo;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Getter
@Builder(toBuilder = true)
@Document(collection = "caves")
public class Cave implements Serializable {

    @Id
    private Long id;

    private String name;

    @DBRef
    private List<Wine> wines;

}
