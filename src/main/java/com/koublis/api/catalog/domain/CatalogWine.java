package com.koublis.api.catalog.domain;

import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@Builder
@Document(indexName = "catalog_wines")
public class CatalogWine {

    @Id
    private String id;

    @Field(type = FieldType.Integer)
    private Integer points;

    @Field(type = FieldType.Text, analyzer = "standard")
    private String title;

    @Field(type = FieldType.Text, analyzer = "standard")
    private String description;

    @Field(type = FieldType.Keyword)
    private String tasterName;

    @Field(type = FieldType.Keyword)
    private String tasterTwitterHandle;

    @Field(type = FieldType.Integer)
    private Integer price;

    @Field(type = FieldType.Text, analyzer = "standard")
    private String designation;

    @Field(type = FieldType.Text, analyzer = "standard")
    private String variety;

    @Field(type = FieldType.Text, analyzer = "standard")
    private String region1;

    @Field(type = FieldType.Text, analyzer = "standard")
    private String region2;

    @Field(type = FieldType.Text, analyzer = "standard")
    private String province;

    @Field(type = FieldType.Text, analyzer = "standard")
    private String country;

    @Field(type = FieldType.Text, analyzer = "standard")
    private String winery;
}
