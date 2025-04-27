package com.koublis.api.catalog.services;

import com.koublis.api.catalog.domain.CatalogWine;
import com.koublis.api.catalog.repositories.CatalogWineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CatalogWineService {
    
    private final CatalogWineRepository catalogWineRepository;
    
    public Page<CatalogWine> searchCatalogWine(Pageable pageable) {
        return catalogWineRepository.findAll(pageable);
    }
}
