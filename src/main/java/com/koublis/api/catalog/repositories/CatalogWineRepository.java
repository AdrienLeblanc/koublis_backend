package com.koublis.api.catalog.repositories;

import com.koublis.api.catalog.domain.CatalogWine;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogWineRepository extends ElasticsearchRepository<CatalogWine, String> {
}
