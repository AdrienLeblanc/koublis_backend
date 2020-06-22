package com.koublis.controller.crud;

import com.koublis.exception.ResourceNotFoundException;
import com.koublis.model.entities.Wine;
import com.koublis.repository.WineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class WineController {

    @Autowired
    private WineRepository wineRepository;

    @GetMapping("/wines")
    public List<Wine> getAllWines() {
        return wineRepository.findAll();
    }

    @PutMapping("/wines")
    public Wine createWine(@Valid @RequestBody Wine wine) {
        return wineRepository.save(wine);
    }

    @PostMapping("/wines/{wineId}")
    public Wine updateWine(@PathVariable Long wineId, @Valid @RequestBody Wine wineRequest) {
        return wineRepository.findById(wineId).map(wine -> {
            wine.setAppellation(wineRequest.getAppellation());
            wine.setAppellation_slug(wineRequest.getAppellation_slug());
            wine.setClassification(wineRequest.getClassification());
            wine.setColor(wineRequest.getColor());
            wine.setConfidence_index(wineRequest.getConfidence_index());
            wine.setCountry(wineRequest.getCountry());
            wine.setDate(wineRequest.getDate());
            wine.setIs_primeurs(wineRequest.isIs_primeurs());
            wine.setJournalist_count(wineRequest.getJournalist_count());
            wine.setLwin(wineRequest.getLwin());
            wine.setLwin_11(wineRequest.getLwin_11());
            wine.setRegions(wineRequest.getRegions());
            wine.setScore(wineRequest.getScore());
            wine.setVintage(wineRequest.getVintage());
            wine.setWine(wineRequest.getWine());
            wine.setWine_id(wineRequest.getWine_id());
            wine.setWine_slug(wineRequest.getWine_slug());
            wine.setWine_type(wineRequest.getWine_type());
            return wineRepository.save(wine);
        }).orElseThrow(() -> new ResourceNotFoundException("WineId " + wineId + " not found"));
    }

    @DeleteMapping("/wines/{wineId}")
    public ResponseEntity<?> deleteWine(@PathVariable Long wineId) {
        return wineRepository.findById(wineId).map(wine -> {
            wineRepository.delete(wine);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("WineId " + wineId + " not found"));
    }
}
