package com.koublis.controller;

import com.koublis.exception.ResourceNotFoundException;
import com.koublis.model.documents.Cave;
import com.koublis.model.documents.Wine;
import com.koublis.repository.CaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api/")
public class CaveController {

    @Autowired
    private CaveRepository caveRepository;

    @GetMapping("/caves")
    public List<Cave> getAllCaves() {
        return caveRepository.findAll();
    }

    @GetMapping("/caves/{caveId}")
    public Cave getCave(@PathVariable Long caveId) {
        return caveRepository.findById(caveId)
                .orElseThrow(() -> new ResourceNotFoundException("CaveId " + caveId + " not found"));
    }

    @PutMapping("/caves")
    public Cave createCave(@Valid @RequestBody Cave cave) {
        return caveRepository.save(cave);
    }

    @PostMapping("/caves/{caveId}")
    public Cave updateCave(@PathVariable Long caveId, @Valid @RequestBody Cave caveRequest) {
        return caveRepository.findById(caveId).map(cave -> {
            cave.setName(caveRequest.getName());
            cave.setWines(caveRequest.getWines());
            return caveRepository.save(cave);
        }).orElseThrow(() -> new ResourceNotFoundException("CaveId " + caveId + " not found"));
    }

    @PutMapping("/caves/{caveId}/addWine")
    public Cave addWineToCave(@PathVariable Long caveId, @Valid @RequestBody Wine wineRequest) {
        return caveRepository.findById(caveId).map(cave -> {
            cave.getWines().add(wineRequest);
            return caveRepository.save(cave);
        }).orElseThrow(() -> new ResourceNotFoundException("CaveId " + caveId + " not found"));
    }

    @DeleteMapping("/caves/{caveId}/removeWine")
    public Cave removeWineFromCave(@PathVariable Long caveId, @Valid @RequestBody Wine wineRequest) {
        return caveRepository.findById(caveId).map(cave -> {
            cave.getWines().remove(wineRequest);
            return caveRepository.save(cave);
        }).orElseThrow(() -> new ResourceNotFoundException("CaveId " + caveId + " not found"));
    }

    @DeleteMapping("/caves/{caveId}")
    public ResponseEntity<?> deleteCave(@PathVariable Long caveId) {
        return caveRepository.findById(caveId).map(cave -> {
            caveRepository.delete(cave);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("CaveId " + caveId + " not found"));
    }
}
