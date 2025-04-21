package com.koublis.controller;

import com.koublis.mappers.CaveMapper;
import com.koublis.services.CaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/caves")
public class CaveController {

    private final CaveService caveService;
    private final CaveMapper caveMapper;

    @GetMapping
    public List<CaveDTO> getAllCaves() {
        return caveService.findAll()
                .stream()
                .map(caveMapper::toCaveDTO)
                .toList();
    }

    @GetMapping("/{cave-id}")
    public CaveDTO getCave(@PathVariable(name = "cave-id") Long caveId) {
        return caveMapper.toCaveDTO(caveService.findCaveById(caveId));
    }

    @PostMapping
    public CaveDTO createCave(@RequestParam(name = "cave-name") String caveName) {
        return caveMapper.toCaveDTO(
                caveService.createCave(caveName)
        );
    }

    @PutMapping("/{cave-id}")
    public CaveDTO updateCave(
            @PathVariable(name = "cave-id") Long caveId,
            @RequestParam(name = "cave-name") String caveName
    ) {
        return caveMapper.toCaveDTO(
                caveService.updateCave(caveId, caveName)
        );
    }

    @DeleteMapping("/{cave-id}")
    public void deleteCave(@PathVariable(name = "cave-id") Long caveId) {
        caveService.deleteCave(caveId);
    }
}
