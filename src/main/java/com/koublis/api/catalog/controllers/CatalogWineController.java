package com.koublis.api.catalog.controllers;

import com.koublis.api.catalog.controllers.dto.CatalogWineDTO;
import com.koublis.api.catalog.mappers.CatalogWineMapper;
import com.koublis.api.catalog.services.CatalogWineService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/catalog/wines")
public class CatalogWineController {

    private final CatalogWineService catalogWineService;
    private final CatalogWineMapper catalogWineMapper;

    @GetMapping("/_search")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("@guardService.isAuthenticated(authentication)")
    public Page<CatalogWineDTO> searchCatalogWines(
            @RequestParam(required = false) String query,
            Pageable pageable
    ) {
        return catalogWineService.searchCatalogWines(query, pageable)
                .map(catalogWineMapper::toCatalogWineDTO);
    }

    @GetMapping("/_search/title")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("@guardService.isAuthenticated(authentication)")
    public Page<CatalogWineDTO> searchCatalogWinesByTitle(
            @RequestParam(value = "q") String query,
            Pageable pageable
    ) {
        return catalogWineService.searchCatalogWinesByTitle(query, pageable)
                .map(catalogWineMapper::toCatalogWineDTO);
    }

    @GetMapping("/_search/tasterName")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("@guardService.isAuthenticated(authentication)")
    public Page<CatalogWineDTO> searchCatalogWinesByTasterName(
            @RequestParam(value = "q") String query,
            Pageable pageable
    ) {
        return catalogWineService.searchCatalogWinesByTasterName(query, pageable)
                .map(catalogWineMapper::toCatalogWineDTO);
    }

    @GetMapping("/_search/designation")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("@guardService.isAuthenticated(authentication)")
    public Page<CatalogWineDTO> searchCatalogWinesByDesignation(
            @RequestParam(value = "q") String query,
            Pageable pageable
    ) {
        return catalogWineService.searchCatalogWinesByDesignation(query, pageable)
                .map(catalogWineMapper::toCatalogWineDTO);
    }

    @GetMapping("/_search/variety")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("@guardService.isAuthenticated(authentication)")
    public Page<CatalogWineDTO> searchCatalogWinesByVariety(
            @RequestParam(value = "q") String query,
            Pageable pageable
    ) {
        return catalogWineService.searchCatalogWinesByVariety(query, pageable)
                .map(catalogWineMapper::toCatalogWineDTO);
    }

    @GetMapping("/_search/province")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("@guardService.isAuthenticated(authentication)")
    public Page<CatalogWineDTO> searchCatalogWinesByProvince(
            @RequestParam(value = "q") String query,
            Pageable pageable
    ) {
        return catalogWineService.searchCatalogWinesByProvince(query, pageable)
                .map(catalogWineMapper::toCatalogWineDTO);
    }

    @GetMapping("/_search/country")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("@guardService.isAuthenticated(authentication)")
    public Page<CatalogWineDTO> searchCatalogWinesByCountry(
            @RequestParam(value = "q") String query,
            Pageable pageable
    ) {
        return catalogWineService.searchCatalogWinesByCountry(query, pageable)
                .map(catalogWineMapper::toCatalogWineDTO);
    }

    @GetMapping("/_search/winery")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("@guardService.isAuthenticated(authentication)")
    public Page<CatalogWineDTO> searchCatalogWinesByWinery(
            @RequestParam(value = "q") String query,
            Pageable pageable
    ) {
        return catalogWineService.searchCatalogWinesByWinery(query, pageable)
                .map(catalogWineMapper::toCatalogWineDTO);
    }
}
