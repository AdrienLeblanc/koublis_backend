package com.koublis.api.catalog.services;

import com.koublis.api.catalog.domain.CatalogWine;
import com.koublis.api.catalog.domain.CatalogWineSearchQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CatalogWineService {

    private final ElasticsearchOperations elasticsearchOperations;

    public Page<CatalogWine> searchCatalogWines(String query, Pageable pageable) {
        log.info("Search {} through elastic catalog wines. {}", query, pageable);
        val searchQuery = CatalogWineSearchQuery.of(pageable)
                .withTitle(query)
                .withDescription(query)
                .withTasterName(query)
                .withDesignation(query)
                .withProvince(query)
                .withCountry(query)
                .withVariety(query)
                .withWinery(query)
                .toQuery();

        val searchHits = elasticsearchOperations.search(searchQuery, CatalogWine.class);
        val content = Optional.of(searchHits)
                .map(SearchHits::getSearchHits)
                .stream()
                .flatMap(List::stream)
                .map(SearchHit::getContent)
                .toList();

        return new PageImpl<>(content, pageable, searchHits.getTotalHits());
    }
}
