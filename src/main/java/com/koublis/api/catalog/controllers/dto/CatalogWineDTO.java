package com.koublis.api.catalog.controllers.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CatalogWineDTO {

    private Integer points;
    private String title;
    private String description;
    private String tasterName;
    private String tasterTwitterHandle;
    private Integer price;
    private String designation;
    private String variety;
    private String region1;
    private String region2;
    private String province;
    private String country;
    private String winery;

}
