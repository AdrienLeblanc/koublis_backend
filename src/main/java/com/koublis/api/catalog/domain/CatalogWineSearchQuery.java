package com.koublis.api.catalog.domain;

import co.elastic.clients.elasticsearch._types.query_dsl.Operator;
import com.koublis.shared.utils.SearchQueryUtils;
import org.springframework.data.domain.Pageable;

public class CatalogWineSearchQuery extends AbstractSearchQuery {

    private CatalogWineSearchQuery(Pageable pageable) {
        super(pageable);
    }

    public static CatalogWineSearchQuery of(Pageable pageable) {
        return new CatalogWineSearchQuery(pageable);
    }

    public CatalogWineSearchQuery withTitle(String title) {
        addClause(SearchQueryUtils.containsFuzzy("title", title), Operator.Or);
        return this;
    }

    public CatalogWineSearchQuery withDescription(String description) {
        addClause(SearchQueryUtils.containsFuzzy("description", description), Operator.Or);
        return this;
    }

    public CatalogWineSearchQuery withTasterName(String tasterName) {
        addClause(SearchQueryUtils.containsFuzzy("tasterName", tasterName), Operator.Or);
        return this;
    }

    public CatalogWineSearchQuery withDesignation(String designation) {
        addClause(SearchQueryUtils.containsFuzzy("designation", designation), Operator.Or);
        return this;
    }

    public CatalogWineSearchQuery withVariety(String variety) {
        addClause(SearchQueryUtils.containsFuzzy("variety", variety), Operator.Or);
        return this;
    }

    public CatalogWineSearchQuery withProvince(String province) {
        addClause(SearchQueryUtils.containsFuzzy("province", province), Operator.Or);
        return this;
    }

    public CatalogWineSearchQuery withCountry(String country) {
        addClause(SearchQueryUtils.containsFuzzy("country", country), Operator.Or);
        return this;
    }

    public CatalogWineSearchQuery withWinery(String winery) {
        addClause(SearchQueryUtils.containsFuzzy("winery", winery), Operator.Or);
        return this;
    }
}
