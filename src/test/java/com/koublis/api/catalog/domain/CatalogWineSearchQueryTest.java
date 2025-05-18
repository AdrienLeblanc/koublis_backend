package com.koublis.api.catalog.domain;

import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryStringQuery;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;

import static org.assertj.core.api.Assertions.assertThat;

class CatalogWineSearchQueryTest {

    private CatalogWineSearchQuery catalogWineSearchQuery;

    @BeforeEach
    void setUp() {
        catalogWineSearchQuery = CatalogWineSearchQuery.of(Pageable.unpaged());
    }

    @Test
    void should_search_title() {
        // GIVEN
        val query = "example";

        // WHEN
        val nativeQuery = catalogWineSearchQuery.withTitle(query).toQuery();

        // THEN
        assertThat(nativeQuery).isNotNull()
                .extracting(NativeQuery::getQuery)
                .isNotNull()
                .extracting(Query::queryString)
                .isNotNull()
                .extracting(QueryStringQuery::query)
                .isEqualTo("(title:" + query + "* OR title:" + query + "~)");
    }

    @Test
    void should_search_title_and_winery() {
        // GIVEN
        val query = "example";

        // WHEN
        val nativeQuery = catalogWineSearchQuery
                .withTitle(query)
                .withWinery(query)
                .toQuery();

        // THEN
        assertThat(nativeQuery).isNotNull()
                .extracting(NativeQuery::getQuery)
                .isNotNull()
                .extracting(Query::queryString)
                .isNotNull()
                .extracting(QueryStringQuery::query)
                .isEqualTo("(title:" + query + "* OR title:" + query + "~) OR (winery:" + query + "* OR winery:" + query + "~)");
    }

    @Test
    void should_search_with_all_fields() {
        // GIVEN
        val query = "example";

        // WHEN
        val nativeQuery = catalogWineSearchQuery
                .withTitle(query)
                .withDescription(query)
                .withTasterName(query)
                .withDesignation(query)
                .withProvince(query)
                .withCountry(query)
                .withVariety(query)
                .withWinery(query)
                .toQuery();

        // THEN
        assertThat(nativeQuery).isNotNull()
                .extracting(NativeQuery::getQuery)
                .isNotNull()
                .extracting(Query::queryString)
                .isNotNull()
                .extracting(QueryStringQuery::query)
                .isEqualTo("(title:" + query + "* OR title:" + query + "~)" +
                        " OR (description:" + query + "* OR description:" + query + "~)" +
                        " OR (tasterName:" + query + "* OR tasterName:" + query + "~)" +
                        " OR (designation:" + query + "* OR designation:" + query + "~)" +
                        " OR (province:" + query + "* OR province:" + query + "~)" +
                        " OR (country:" + query + "* OR country:" + query + "~)" +
                        " OR (variety:" + query + "* OR variety:" + query + "~)" +
                        " OR (winery:" + query + "* OR winery:" + query + "~)"
                );
    }
}