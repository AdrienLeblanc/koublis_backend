package com.koublis.api.catalog.services;

import com.koublis.api.catalog.domain.CatalogWine;
import com.koublis.shared.services.ElasticsearchIndexer;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Service;

@Service
public class ElasticsearchCatalogWineService extends ElasticsearchIndexer<CatalogWine> {

    public ElasticsearchCatalogWineService(final ElasticsearchOperations elasticsearchOperations) {
        super(elasticsearchOperations, CatalogWine.class);
    }

    public void reindex() {
        indexDocuments();
    }
}
