package com.koublis.api.auth.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Builder
@Document(collection = "roles")
public class Role {

    @Id
    private String id;

    private ERole name;

}