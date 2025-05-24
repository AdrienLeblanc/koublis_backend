package com.koublis.api.catalog.services;

import com.koublis.api.catalog.domain.CatalogWine;
import com.koublis.api.catalog.domain.CatalogWineSearchQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
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

        return searchCatalogWines(searchQuery, pageable);
    }

    public Page<CatalogWine> searchCatalogWinesByTitle(String title, Pageable pageable) {
        log.info("Search catalog wines by title: {} with pageable: {}", title, pageable);
        val searchQuery = CatalogWineSearchQuery.of(pageable)
                .withTitle(title)
                .toQuery();

        return searchCatalogWines(searchQuery, pageable);
    }

    public Page<CatalogWine> searchCatalogWinesByDescription(String description, Pageable pageable) {
        log.info("Search catalog wines by description: {} with pageable: {}", description, pageable);
        val searchQuery = CatalogWineSearchQuery.of(pageable)
                .withDescription(description)
                .toQuery();

        return searchCatalogWines(searchQuery, pageable);
    }

    public Page<CatalogWine> searchCatalogWinesByTasterName(String tasterName, Pageable pageable) {
        log.info("Search catalog wines by taster name: {} with pageable: {}", tasterName, pageable);
        val searchQuery = CatalogWineSearchQuery.of(pageable)
                .withTasterName(tasterName)
                .toQuery();

        return searchCatalogWines(searchQuery, pageable);
    }

    public Page<CatalogWine> searchCatalogWinesByDesignation(String designation, Pageable pageable) {
        log.info("Search catalog wines by designation: {} with pageable: {}", designation, pageable);
        val searchQuery = CatalogWineSearchQuery.of(pageable)
                .withDesignation(designation)
                .toQuery();

        return searchCatalogWines(searchQuery, pageable);
    }

    public Page<CatalogWine> searchCatalogWinesByVariety(String variety, Pageable pageable) {
        log.info("Search catalog wines by variety: {} with pageable: {}", variety, pageable);
        val searchQuery = CatalogWineSearchQuery.of(pageable)
                .withVariety(variety)
                .toQuery();

        return searchCatalogWines(searchQuery, pageable);
    }

    public Page<CatalogWine> searchCatalogWinesByProvince(String province, Pageable pageable) {
        log.info("Search catalog wines by province: {} with pageable: {}", province, pageable);
        val searchQuery = CatalogWineSearchQuery.of(pageable)
                .withProvince(province)
                .toQuery();

        return searchCatalogWines(searchQuery, pageable);
    }

    public Page<CatalogWine> searchCatalogWinesByCountry(String country, Pageable pageable) {
        log.info("Search catalog wines by country: {} with pageable: {}", country, pageable);
        val searchQuery = CatalogWineSearchQuery.of(pageable)
                .withCountry(country)
                .toQuery();

        return searchCatalogWines(searchQuery, pageable);
    }

    public Page<CatalogWine> searchCatalogWinesByWinery(String winery, Pageable pageable) {
        log.info("Search catalog wines by winery: {} with pageable: {}", winery, pageable);
        val searchQuery = CatalogWineSearchQuery.of(pageable)
                .withWinery(winery)
                .toQuery();

        return searchCatalogWines(searchQuery, pageable);
    }

    /**
     * Executes the search query and returns a paginated result of CatalogWine.
     *
     * @param searchQuery the search query to execute
     * @param pageable    the pagination information
     * @return a Page containing CatalogWine results
     */
    private PageImpl<CatalogWine> searchCatalogWines(NativeQuery searchQuery, Pageable pageable) {
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
