package com.koublis.api.catalog.services;

import com.koublis.api.catalog.domain.CatalogWine;
import com.koublis.api.catalog.repositories.CatalogWineRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CatalogWineService {
    
    private final CatalogWineRepository catalogWineRepository;
    
    public Page<CatalogWine> searchCatalogWine(String query, Pageable pageable) {
        log.info("Search {} through catalog wines. {}", query, pageable);
        return catalogWineRepository.search(query, pageable);
    }
}
