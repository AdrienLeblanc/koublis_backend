package com.koublis.controller;

import com.koublis.entities.Wine;
import com.koublis.repository.WineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WineController {

    @Autowired
    private WineRepository wineRepository;

    @GetMapping("/wines")
    public List<Wine> findAllWines() {
        return wineRepository.findAll();
    }

    @GetMapping("/wine")
    public Wine findById(@RequestParam(value="id") long id) {
        return wineRepository.findById(id);
    }

    @PostMapping("/addWine")
    void addUser(@RequestBody Wine wine) {
        wineRepository.save(wine);
    }
}
