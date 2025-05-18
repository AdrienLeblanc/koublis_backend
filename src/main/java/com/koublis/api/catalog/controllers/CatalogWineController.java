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
}
