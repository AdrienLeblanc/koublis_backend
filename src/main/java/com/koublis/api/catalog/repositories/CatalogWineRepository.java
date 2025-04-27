package com.koublis.api.catalog.repositories;

import com.koublis.api.catalog.domain.CatalogWine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CatalogWineRepository extends JpaRepository<CatalogWine, UUID> {
    
    /**
     * Search for wines in the catalog based on a query string.
     *
     * @param query   The search query string.
     * @param pageable The pagination information.
     * @return A page of CatalogWine entities matching the search criteria.
     */
    @Query("SELECT w FROM CatalogWine w WHERE " +
            "(:query IS NULL OR LOWER(w.title) LIKE LOWER(CONCAT('%', :query, '%'))) OR " +
            "(:query IS NULL OR LOWER(w.tasterName) LIKE LOWER(CONCAT('%', :query, '%'))) OR " +
            "(:query IS NULL OR LOWER(w.tasterTwitterHandle) LIKE LOWER(CONCAT('%', :query, '%'))) OR " +
            "(:query IS NULL OR LOWER(w.designation) LIKE LOWER(CONCAT('%', :query, '%'))) OR " +
            "(:query IS NULL OR LOWER(w.variety) LIKE LOWER(CONCAT('%', :query, '%'))) OR " +
            "(:query IS NULL OR LOWER(w.region1) LIKE LOWER(CONCAT('%', :query, '%'))) OR " +
            "(:query IS NULL OR LOWER(w.region2) LIKE LOWER(CONCAT('%', :query, '%'))) OR " +
            "(:query IS NULL OR LOWER(w.province) LIKE LOWER(CONCAT('%', :query, '%'))) OR " +
            "(:query IS NULL OR LOWER(w.country) LIKE LOWER(CONCAT('%', :query, '%'))) OR " +
            "(:query IS NULL OR LOWER(w.winery) LIKE LOWER(CONCAT('%', :query, '%')))"
    )
    Page<CatalogWine> search(String query, Pageable pageable);
    
}
