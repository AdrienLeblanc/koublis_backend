package com.koublis.api.wine.controllers;

import com.koublis.AbstractControllerTest;
import com.koublis.api.wine.domain.Wine;
import com.koublis.api.wine.mappers.WineMapper;
import com.koublis.api.wine.services.WineService;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = WineController.class)
class WineControllerTest extends AbstractControllerTest {

    @MockitoBean
    private WineService wineService;

    @MockitoSpyBean
    private WineMapper wineMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void should_return_200_find_all_wines() throws Exception {
        // GIVEN
        val caveId = UUID.randomUUID();
        val wineId = UUID.randomUUID();
        when(wineService.findAllByCaveId(caveId)).thenReturn(List.of(
                Wine.builder()
                        .id(wineId)
                        .name("Wine")
                        .build()
        ));

        // WHEN
        mockMvc.perform(get("/caves/{cave-id}/wines", caveId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].id").value(wineId.toString()))
                .andExpect(jsonPath("$.[0].name").value("Wine"));

        // THEN
        verify(wineService).findAllByCaveId(caveId);
    }

    @Test
    void should_return_200_find_wine_by_id() throws Exception {
        // GIVEN
        val caveId = UUID.randomUUID();
        val wineId = UUID.randomUUID();
        when(wineService.findByCaveIdAndWineId(caveId, wineId)).thenReturn(
                Wine.builder()
                        .id(wineId)
                        .name("Wine")
                        .build()
        );

        // WHEN
        mockMvc.perform(get("/caves/{cave-id}/wines/{wine-id}", caveId, wineId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(wineId.toString()))
                .andExpect(jsonPath("$.name").value("Wine"));

        // THEN
        verify(wineService).findByCaveIdAndWineId(caveId, wineId);
    }

    @Test
    void should_return_201_create_wine() throws Exception {
        // GIVEN
        val caveId = UUID.randomUUID();
        val wineId = UUID.randomUUID();
        val wineJson = """
                {
                    "name": "Wine"
                }
                """;
        when(wineService.createWine(eq(caveId), any())).thenReturn(
                Wine.builder()
                        .id(wineId)
                        .name("Wine")
                        .build()
        );

        // WHEN
        mockMvc.perform(post("/caves/{cave-id}/wines", caveId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(wineJson)
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(wineId.toString()))
                .andExpect(jsonPath("$.name").value("Wine"));

        // THEN
        val captor = ArgumentCaptor.forClass(Wine.class);
        verify(wineService).createWine(eq(caveId), captor.capture());
        assertThat(captor.getValue().getName()).isEqualTo("Wine");
    }

    @Test
    void should_return_200_update_wine() throws Exception {
        // GIVEN
        val caveId = UUID.randomUUID();
        val wineId = UUID.randomUUID();
        val wineJson = """
                {
                    "id": "%s",
                    "name": "Wine"
                }
                """
                .formatted(wineId);
        when(wineService.updateWine(eq(caveId), eq(wineId), any())).thenReturn(
                Wine.builder()
                        .id(wineId)
                        .name("Wine")
                        .build()
        );

        // WHEN
        mockMvc.perform(put("/caves/{cave-id}/wines/{wine-id}", caveId, wineId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(wineJson)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(wineId.toString()))
                .andExpect(jsonPath("$.name").value("Wine"));

        // THEN
        val captor = ArgumentCaptor.forClass(Wine.class);
        verify(wineService).updateWine(eq(caveId), eq(wineId), captor.capture());
        assertThat(captor.getValue().getId()).isEqualTo(wineId);
        assertThat(captor.getValue().getName()).isEqualTo("Wine");
    }

    @Test
    void should_return_204_delete_wine() throws Exception {
        // GIVEN
        val caveId = UUID.randomUUID();
        val wineId = UUID.randomUUID();

        // WHEN
        mockMvc.perform(delete("/caves/{cave-id}/wines/{wine-id}", caveId, wineId))
                .andExpect(status().isNoContent());

        // THEN
        verify(wineService).deleteWine(caveId, wineId);
    }
}