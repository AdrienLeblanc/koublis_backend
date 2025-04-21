package com.koublis.services;

import com.koublis.exception.ResourceNotFoundException;
import com.koublis.model.documents.Cave;
import com.koublis.repository.CaveRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Cave findCaveById(Long caveId) {
        log.debug("Finding cave with id {}", caveId);
        return caveRepository.findById(caveId)
                .orElseThrow(() -> new ResourceNotFoundException("Cave with id " + caveId + " not found"));
    }

    public Cave updateCave(Long caveId, String caveName) {
        log.debug("Updating cave with id {}. Name={}", caveId, caveName);
        val found = this.findCaveById(caveId);

        return caveRepository.save(
                found.toBuilder()
                        .name(caveName)
                        .build()
        );
    }

    public void deleteCave(Long caveId) {
        log.debug("Deleting cave with id {}", caveId);
        caveRepository.deleteById(caveId);
    }

    public void deleteAllCaves() {
        log.debug("Deleting all caves");
        caveRepository.deleteAll();
    }
}
