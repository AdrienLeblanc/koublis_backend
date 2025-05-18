package com.koublis.api.catalog.controllers;

import com.koublis.AbstractControllerTest;
import com.koublis.api.catalog.domain.CatalogWine;
import com.koublis.api.catalog.mappers.CatalogWineMapper;
import com.koublis.api.catalog.services.CatalogWineService;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = CatalogWineController.class)
class CatalogWineControllerTest extends AbstractControllerTest {

    @MockitoBean
    private CatalogWineService catalogWineService;

    @MockitoSpyBean
    private CatalogWineMapper catalogWineMapper;

    @Autowired
    private MockMvc mockMvc;
    
    @Test
    void should_return_200_search_catalog_wines() throws Exception {
        // GIVEN
        val query = "example";
        when(catalogWineService.searchCatalogWines(any(), any())).thenReturn(
                new PageImpl<>(
                        List.of(CatalogWine.builder()
                                        .title("Catalog Wine")
                                .build())
                )
        );

        // WHEN
        mockMvc.perform(get("/catalog/wines/_search")
                        .param("query", query)
                        .param("page", "3")
                        .param("size", "15")
                        .param("sort", "points,desc")
                )
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.totalElements").value(1))
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content[0].title").value("Catalog Wine"));
        
        // THEN
        val captor = ArgumentCaptor.forClass(Pageable.class);
        verify(catalogWineService).searchCatalogWines(eq(query), captor.capture());
        assertThat(captor.getValue().getPageNumber()).isEqualTo(3);
        assertThat(captor.getValue().getPageSize()).isEqualTo(15);
        assertThat(captor.getValue().getSort().toString()).isEqualTo("points: DESC");
    }

}