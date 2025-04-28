package com.koublis.api.wine.services;

import com.koublis.api.wine.domain.Wine;
import com.koublis.api.wine.repositories.CaveRepository;
import com.koublis.api.wine.repositories.WineRepository;
import com.koublis.configuration.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class WineService {

    private final CaveService caveService;
    private final WineRepository wineRepository;
    private final CaveRepository caveRepository;

    public List<Wine> findAllByCaveId(UUID caveId) {
        log.debug("Finding all wines for cave with id {}", caveId);
        return wineRepository.findAllByCaveId(caveId);
    }

    public Wine findByCaveIdAndWineId(UUID caveId, UUID wineId) {
        log.debug("Finding wine with id {} for cave with id {}", wineId, caveId);
        return wineRepository.findByCaveIdAndId(caveId, wineId)
                .orElseThrow(() -> new ResourceNotFoundException("WineId " + wineId + " not found for caveId " + caveId));
    }

    public Wine createWine(UUID caveId, Wine wineRequest) {
        val cave = caveService.findCaveById(caveId);

        log.debug("Creating wine for cave with id {}. Body={}", caveId, wineRequest);
        return wineRepository.save(
                wineRequest
                        .withId(null)
                        .withCave(cave)
        );
    }

    public Wine updateWine(UUID caveId, UUID wineId, Wine wineRequest) {
        val wineFound = this.findByCaveIdAndWineId(caveId, wineId);

        log.debug("Updating wine with id {} for cave with id {}. Body={}", wineId, caveId, wineRequest);
        return wineRepository.save(wineFound
                .withCount(wineRequest.getCount())
                .withName(wineRequest.getName())
                .withVintage(wineRequest.getVintage())
                .withCountry(wineRequest.getCountry())
                .withColor(wineRequest.getColor())
                .withRegions(wineRequest.getRegions())
                .withClassification(wineRequest.getClassification())
                .withPrimeur(wineRequest.getPrimeur())
        );
    }

    public void deleteWine(UUID caveId, UUID wineId) {
        log.debug("Deleting wine with id {} for cave with id {}", wineId, caveId);
        wineRepository.deleteByCaveIdAndId(caveId, wineId);
    }
}
