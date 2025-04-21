package com.koublis.mappers;

import com.koublis.controller.WineDTO;
import com.koublis.model.documents.Wine;
import org.springframework.stereotype.Component;

import java.util.List;

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
                .isPrimeurs(wineDto.getIsPrimeurs())
                .journalistCount(wineDto.getJournalistCount())
                .lwin(wineDto.getLwin())
                .lwin11(wineDto.getLwin11())
                .regions(wineDto.getRegions())
                .score(wineDto.getScore())
                .vintage(wineDto.getVintage())
                .wine(wineDto.getWine())
                .wineId(wineDto.getWineId())
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
                .isPrimeurs(wine.getIsPrimeurs())
                .journalistCount(wine.getJournalistCount())
                .lwin(wine.getLwin())
                .lwin11(wine.getLwin11())
                .regions(wine.getRegions())
                .score(wine.getScore())
                .vintage(wine.getVintage())
                .wine(wine.getWine())
                .wineId(wine.getWineId())
                .wineSlug(wine.getWineSlug())
                .wineType(wine.getWineType())
                .build();
    }

}
