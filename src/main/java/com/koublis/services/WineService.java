package com.koublis.services;

import com.koublis.exception.ResourceNotFoundException;
import com.koublis.model.documents.Wine;
import com.koublis.repository.WineRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class WineService {

    private final CaveService caveService;
    private final WineRepository wineRepository;

    public List<Wine> findAllByCaveId(Long caveId) {
        log.debug("Finding all wines for cave with id {}", caveId);
        return wineRepository.findAllByCaveId(caveId);
    }

    public Wine findByCaveIdAndWineId(Long caveId, Long wineId) {
        log.debug("Finding wine with id {} for cave with id {}", wineId, caveId);
        return wineRepository.findByCaveIdAndWineId(caveId, wineId)
                .orElseThrow(() -> new ResourceNotFoundException("WineId " + wineId + " not found for caveId " + caveId));
    }

    public Wine createWine(Long caveId, Wine wineRequest) {
        val cave = caveService.findCaveById(caveId);

        log.debug("Creating wine for cave with id {}. Body={}", caveId, wineRequest);
        return wineRepository.save(
                wineRequest.toBuilder()
                        .cave(cave)
                        .wineId(null)
                        .build()
        );
    }

    public Wine updateWine(Long caveId, Long wineId, Wine wineRequest) {
        val wineFound = this.findByCaveIdAndWineId(caveId, wineId);

        log.debug("Updating wine with id {} for cave with id {}. Body={}", wineId, caveId, wineRequest);
        return wineRepository.save(
                wineRequest.toBuilder()
                        .wineId(wineFound.getWineId())
                        .build()
        );
    }

    public void deleteWine(Long caveId, Long wineId) {
        wineRepository.deleteByCaveIdAndWineId(caveId, wineId);
    }
}
