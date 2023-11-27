package com.koublis.model.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Document(collection = "caves")
public class Cave implements Serializable {

    @Id
    private Long id;

    private String name;

    @DBRef
    private Set<Wine> wines = new HashSet<>();

    public Cave() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Wine> getWines() {
        return wines;
    }

    public void setWines(Set<Wine> wines) {
        this.wines = wines;
    }
}
