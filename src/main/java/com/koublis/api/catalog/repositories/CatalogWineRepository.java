package com.koublis.api.catalog.repositories;

import com.koublis.api.catalog.domain.CatalogWine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CatalogWineRepository extends JpaRepository<CatalogWine, UUID> {
    
}
