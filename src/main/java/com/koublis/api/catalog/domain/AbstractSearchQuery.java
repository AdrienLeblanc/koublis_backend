package com.koublis.api.catalog.domain;

import co.elastic.clients.elasticsearch._types.query_dsl.Operator;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;

import static com.koublis.shared.utils.SearchQueryUtils.add;

public abstract class AbstractSearchQuery {

    protected final Pageable pageable;
    protected final StringBuilder searchQuery = new StringBuilder();

    protected AbstractSearchQuery(Pageable pageable) {
        this.pageable = pageable;
    }

    public void addClause(String clause, Operator operator) {
        if (searchQuery.isEmpty()) {
            searchQuery.append(clause);
        } else {
            searchQuery.append(add(clause, operator));
        }
    }

    public NativeQuery toQuery() {
        return NativeQuery.builder()
                .withQuery(
                        QueryBuilders.queryString()
                                .query(searchQuery.toString())
                                .build()
                                ._toQuery()
                )
                .withPageable(pageable)
                .build();
    }

}
