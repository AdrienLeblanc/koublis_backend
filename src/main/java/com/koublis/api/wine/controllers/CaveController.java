package com.koublis.api.wine.controllers;

import com.koublis.api.wine.controllers.dto.CaveDTO;
import com.koublis.api.wine.mappers.CaveMapper;
import com.koublis.api.wine.services.CaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/caves")
public class CaveController {

    private final CaveService caveService;
    private final CaveMapper caveMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("@guardService.isAuthenticated(authentication)")
    public List<CaveDTO> findAllCaves() {
        return caveService.findAll()
                .stream()
                .map(caveMapper::toCaveDTO)
                .toList();
    }

    @GetMapping("/{cave-id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("@guardService.isAuthenticated(authentication)")
    public CaveDTO findCaveById(@PathVariable(name = "cave-id") UUID caveId) {
        return caveMapper.toCaveDTO(caveService.findCaveById(caveId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("@guardService.isAuthenticated(authentication)")
    public CaveDTO createCave(@RequestParam(name = "cave-name") String caveName) {
        return caveMapper.toCaveDTO(
                caveService.createCave(caveName)
        );
    }

    @PutMapping("/{cave-id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("@guardService.isAuthenticated(authentication)")
    public CaveDTO updateCave(
            @PathVariable(name = "cave-id") UUID caveId,
            @RequestParam(name = "cave-name") String caveName
    ) {
        return caveMapper.toCaveDTO(
                caveService.updateCave(caveId, caveName)
        );
    }

    @DeleteMapping("/{cave-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCave(@PathVariable(name = "cave-id") UUID caveId) {
        caveService.deleteCave(caveId);
    }
}
