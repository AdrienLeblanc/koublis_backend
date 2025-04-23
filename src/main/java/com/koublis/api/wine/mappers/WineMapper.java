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
                .appellationSlug(wineDto.getAppellationSlug())
                .classification(wineDto.getClassification())
                .color(wineDto.getColor())
                .confidenceIndex(wineDto.getConfidenceIndex())
                .country(wineDto.getCountry())
                .date(wineDto.getDate())
                .id(wineDto.getId())
                .isPrimeurs(wineDto.getIsPrimeurs())
                .journalistCount(wineDto.getJournalistCount())
                .lwin(wineDto.getLwin())
                .lwin11(wineDto.getLwin11())
                .regions(wineDto.getRegions()
                        .stream()
                        .reduce((a, b) -> a + "," + b)
                        .orElse(null)
                )
                .score(wineDto.getScore())
                .vintage(wineDto.getVintage())
                .wine(wineDto.getWine())
                .wineSlug(wineDto.getWineSlug())
                .wineType(wineDto.getWineType())
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
                .appellationSlug(wine.getAppellationSlug())
                .classification(wine.getClassification())
                .color(wine.getColor())
                .confidenceIndex(wine.getConfidenceIndex())
                .country(wine.getCountry())
                .date(wine.getDate())
                .id(wine.getId())
                .isPrimeurs(wine.getIsPrimeurs())
                .journalistCount(wine.getJournalistCount())
                .lwin(wine.getLwin())
                .lwin11(wine.getLwin11())
                .regions(Optional.ofNullable(wine.getRegions())
                        .map(regionsStr -> regionsStr.split(","))
                        .map(List::of)
                        .orElse(null))
                .score(wine.getScore())
                .vintage(wine.getVintage())
                .wine(wine.getWine())
                .wineSlug(wine.getWineSlug())
                .wineType(wine.getWineType())
                .build();
    }

}
