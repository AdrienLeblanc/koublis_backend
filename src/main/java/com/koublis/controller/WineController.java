package com.koublis.controller;

import com.koublis.entities.Wine;
import com.koublis.repository.WineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class WineController {

    @Autowired
    private WineRepository wineRepository;

    @GetMapping("/wines")
    public List<Wine> findAll() {
        return wineRepository.findAll();
    }

    @PostMapping("/addWine")
    void addWine(@RequestBody Wine wine) {
        wineRepository.save(wine);
    }
}
