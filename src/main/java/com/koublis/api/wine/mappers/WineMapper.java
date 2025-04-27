package com.koublis.api.wine.mappers;

import com.koublis.api.wine.controllers.dto.WineDTO;
import com.koublis.api.wine.domain.Wine;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class WineMapper {

    public List<Wine> toWines(List<WineDTO> wineDTOs) {
        return Optional.ofNullable(wineDTOs)
                .stream()
                .flatMap(List::stream)
                .map(this::toWine)
                .toList();
    }

    public Wine toWine(WineDTO wineDto) {
        return Wine.builder()
                .id(wineDto.getId())
                .count(wineDto.getCount())
                .name(wineDto.getName())
                .vintage(wineDto.getVintage())
                .country(wineDto.getCountry())
                .color(wineDto.getColor())
                .regions(Optional.ofNullable(wineDto.getRegions())
                        .stream()
                        .flatMap(List::stream)
                        .reduce((a, b) -> a + "," + b)
                        .orElse(null)
                )
                .classification(wineDto.getClassification())
                .primeur(wineDto.getPrimeur())
                .build();
    }

    public List<WineDTO> toWineDTOs(List<Wine> wineDTOs) {
        return Optional.ofNullable(wineDTOs)
                .stream()
                .flatMap(List::stream)
                .map(this::toWineDTO)
                .toList();
    }

    public WineDTO toWineDTO(Wine wine) {
        return WineDTO.builder()
                .id(wine.getId())
                .count(wine.getCount())
                .name(wine.getName())
                .vintage(wine.getVintage())
                .country(wine.getCountry())
                .color(wine.getColor())
                .regions(Optional.ofNullable(wine.getRegions())
                        .map(regionsStr -> regionsStr.split(","))
                        .map(List::of)
                        .orElse(null))
                .classification(wine.getClassification())
                .primeur(wine.getPrimeur())
                .build();
    }

}
