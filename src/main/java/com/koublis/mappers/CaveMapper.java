package com.koublis.mappers;

import com.koublis.controllers.dto.CaveDTO;
import com.koublis.domain.mongo.Cave;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CaveMapper {

    private final WineMapper wineMapper;

    public Cave toCave(CaveDTO caveDto) {
        return Cave.builder()
                .id(caveDto.getId())
                .name(caveDto.getName())
                .wines(wineMapper.toWines(caveDto.getWines()))
                .build();
    }

    public CaveDTO toCaveDTO(Cave cave) {
        return CaveDTO.builder()
                .id(cave.getId())
                .name(cave.getName())
                .wines(wineMapper.toWineDTOs(cave.getWines()))
                .build();
    }
}
