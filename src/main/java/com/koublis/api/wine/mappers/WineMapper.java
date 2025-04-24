package com.koublis.api.wine.mappers;

import com.koublis.api.wine.controllers.dto.WineDTO;
import com.koublis.api.wine.domain.Wine;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class WineMapper {

    public List<Wine> toWines(List<WineDTO> wineDTOs) {
        return wineDTOs.stream()
                .map(this::toWine)
                .toList();
    }

    public Wine toWine(WineDTO wineDto) {
        return Wine.builder()
                .appellation(wineDto.getAppellation())
                .classification(wineDto.getClassification())
                .color(wineDto.getColor())
                .country(wineDto.getCountry())
                .date(wineDto.getDate())
                .id(wineDto.getId())
                .isPrimeur(wineDto.getIsPrimeur())
                .regions(wineDto.getRegions()
                        .stream()
                        .reduce((a, b) -> a + "," + b)
                        .orElse(null)
                )
                .vintage(wineDto.getVintage())
                .build();
    }

    public List<WineDTO> toWineDTOs(List<Wine> wineDTOs) {
        return wineDTOs.stream()
                .map(this::toWineDTO)
                .toList();
    }

    public WineDTO toWineDTO(Wine wine) {
        return WineDTO.builder()
                .appellation(wine.getAppellation())
                .classification(wine.getClassification())
                .color(wine.getColor())
                .country(wine.getCountry())
                .date(wine.getDate())
                .id(wine.getId())
                .isPrimeur(wine.getIsPrimeur())
                .regions(Optional.ofNullable(wine.getRegions())
                        .map(regionsStr -> regionsStr.split(","))
                        .map(List::of)
                        .orElse(null))
                .vintage(wine.getVintage())
                .build();
    }

}
