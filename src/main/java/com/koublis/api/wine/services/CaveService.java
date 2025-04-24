package com.koublis.api.wine.services;

import com.koublis.api.wine.domain.Cave;
import com.koublis.api.wine.repositories.CaveRepository;
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
public class CaveService {

    private final CaveRepository caveRepository;

    public List<Cave> findAll() {
        log.debug("Finding all caves");
        return caveRepository.findAll();
    }

    public Cave createCave(String caveName) {
        log.debug("Creating cave with name {}", caveName);
        val cave = Cave.builder()
                .name(caveName)
                .build();

        return caveRepository.save(cave);
    }

    public Cave findCaveById(UUID caveId) {
        log.debug("Finding cave with id {}", caveId);
        return caveRepository.findById(caveId)
                .orElseThrow(() -> new ResourceNotFoundException("Cave with id " + caveId + " not found"));
    }

    public Cave updateCave(UUID caveId, String caveName) {
        log.debug("Updating cave with id {}. Name={}", caveId, caveName);
        val found = this.findCaveById(caveId);

        return caveRepository.save(
                found.withName(caveName)
        );
    }

    public void deleteCave(UUID caveId) {
        log.debug("Deleting cave with id {}", caveId);
        caveRepository.deleteById(caveId);
    }

    public void deleteAllCaves() {
        log.debug("Deleting all caves");
        caveRepository.deleteAll();
    }
}
