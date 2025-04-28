package com.koublis.api.wine.controllers;

import com.koublis.api.wine.controllers.dto.WineDTO;
import com.koublis.api.wine.mappers.WineMapper;
import com.koublis.api.wine.services.WineService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/caves/{cave-id}/wines")
public class WineController {

    private final WineService wineService;
    private final WineMapper wineMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("@guardService.isAuthentified(authentication)")
    public List<WineDTO> findAllWines(
            @PathVariable(value = "cave-id") UUID caveId
    ) {
        return wineService.findAllByCaveId(caveId)
                .stream()
                .map(wineMapper::toWineDTO)
                .toList();
    }

    @GetMapping("/{wine-id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("@guardService.isAuthentified(authentication)")
    public WineDTO findWineById(
            @PathVariable(value = "cave-id") UUID caveId,
            @PathVariable(name = "wine-id") UUID wineId) {
        return wineMapper.toWineDTO(wineService.findByCaveIdAndWineId(caveId, wineId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("@guardService.isAuthentified(authentication)")
    public WineDTO createWine(
            @PathVariable(value = "cave-id") UUID caveId,
            @Valid @RequestBody WineDTO wine
    ) {
        return wineMapper.toWineDTO(
                wineService.createWine(caveId, wineMapper.toWine(wine))
        );
    }

    @PutMapping("/{wine-id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("@guardService.isAuthentified(authentication)")
    public WineDTO updateWine(
            @PathVariable(value = "cave-id") UUID caveId,
            @PathVariable(name = "wine-id") UUID wineId,
            @Valid @RequestBody WineDTO wineRequest
    ) {
        return wineMapper.toWineDTO(
                wineService.updateWine(caveId, wineId, wineMapper.toWine(wineRequest))
        );
    }

    @DeleteMapping("/{wine-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("@guardService.isAuthentified(authentication)")
    public void deleteWine(
            @PathVariable(value = "cave-id") UUID caveId,
            @PathVariable(name = "wine-id") UUID wineId
    ) {
        wineService.deleteWine(caveId, wineId);
    }
}
