package com.koublis.shared.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

@Slf4j
@RequiredArgsConstructor
public abstract class ElasticsearchIndexer<T> {

    protected final ElasticsearchOperations elasticsearchOperations;
    private final Class<T> entityClass;

    protected void indexDocuments() {
        val indexOps = elasticsearchOperations.indexOps(entityClass);
        if (!indexOps.exists()) {
            createIndex(entityClass);
        } else {
            log.info("Index already exists for entity class: {}", entityClass.getSimpleName());
            indexOps.refresh();
            log.info("Index refreshed for entity class: {}", entityClass.getSimpleName());
        }

    }

    protected void createIndex(Class<?> entityClass) {
        log.info("Created index for entity class: {}", entityClass.getSimpleName());
        val indexOps = elasticsearchOperations.indexOps(entityClass);
        indexOps.create();
        indexOps.putMapping(indexOps.createMapping());
        log.info("Successfully created index for entity class: {}", entityClass.getSimpleName());
    }
}
