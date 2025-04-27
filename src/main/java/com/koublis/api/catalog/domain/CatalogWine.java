package com.koublis.api.catalog.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "catalog_wines")
@With
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CatalogWine {

    @Id
    @UuidGenerator
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "BINARY(16)")
    @JsonProperty("id")
    private UUID id;
    
    @Column(name = "points")
    @JsonProperty("points")
    private Integer points;
    
    @Column(name = "title")
    @JsonProperty("title")
    private String title;
    
    @Column(name = "description", columnDefinition = "LONGTEXT")
    @JsonProperty("description")
    private String description;
    
    @Column(name = "taster_name")
    @JsonProperty("taster_name")
    private String tasterName;
    
    @Column(name = "taster_twitter_handle")
    @JsonProperty("taster_twitter_handle")
    private String tasterTwitterHandle;
    
    @Column(name = "price")
    @JsonProperty("price")
    private Integer price;
    
    @Column(name = "designation")
    @JsonProperty("designation")
    private String designation;
    
    @Column(name = "variety")
    @JsonProperty("variety")
    private String variety;
    
    @Column(name = "region_1")
    @JsonProperty("region_1")
    private String region1;
    
    @Column(name = "region_2")
    @JsonProperty("region_2")
    private String region2;
    
    @Column(name = "province")
    @JsonProperty("province")
    private String province;
    
    @Column(name = "country")
    @JsonProperty("country")
    private String country;
    
    @Column(name = "winery")
    @JsonProperty("winery")
    private String winery;

}
