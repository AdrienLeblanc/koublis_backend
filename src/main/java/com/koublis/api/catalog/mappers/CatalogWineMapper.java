package com.koublis.api.catalog.mappers;

import com.koublis.api.catalog.controllers.dto.CatalogWineDTO;
import com.koublis.api.catalog.domain.CatalogWine;
import org.springframework.stereotype.Component;

@Component
public class CatalogWineMapper {
    
    public CatalogWineDTO toCatalogWineDTO(CatalogWine catalogWine) {
        return CatalogWineDTO.builder()
                .title(catalogWine.getTitle())
                .description(catalogWine.getDescription())
                .tasterName(catalogWine.getTasterName())
                .tasterTwitterHandle(catalogWine.getTasterTwitterHandle())
                .points(catalogWine.getPoints())
                .price(catalogWine.getPrice())
                .designation(catalogWine.getDesignation())
                .variety(catalogWine.getVariety())
                .region1(catalogWine.getRegion1())
                .region2(catalogWine.getRegion2())
                .province(catalogWine.getProvince())
                .country(catalogWine.getCountry())
                .winery(catalogWine.getWinery())
                .build();
    }
}
