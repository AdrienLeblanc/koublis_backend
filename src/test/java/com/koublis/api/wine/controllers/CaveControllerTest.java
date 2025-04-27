package com.koublis.api.wine.controllers;

import com.koublis.AbstractControllerTest;
import com.koublis.api.wine.domain.Cave;
import com.koublis.api.wine.mappers.CaveMapper;
import com.koublis.api.wine.services.CaveService;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = CaveController.class)
class CaveControllerTest extends AbstractControllerTest {

    @MockitoBean
    private CaveService caveService;

    @MockitoSpyBean
    private CaveMapper caveMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void should_return_200_find_all_caves() throws Exception {
        // Given
        val caveId = UUID.randomUUID();
        when(caveService.findAll()).thenReturn(List.of(
                Cave.builder()
                        .id(caveId)
                        .name("Cave")
                        .build()
        ));

        // When
        mockMvc.perform(get("/caves"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].id").value(caveId.toString()))
                .andExpect(jsonPath("$.[0].name").value("Cave"));

        // Then
        verify(caveService).findAll();
    }
    
    @Test
    void should_return_200_find_cave_by_id() throws Exception {
        // Given
        val caveId = UUID.randomUUID();
        when(caveService.findCaveById(caveId)).thenReturn(
                Cave.builder()
                        .id(caveId)
                        .name("Cave")
                        .build()
        );

        // When
        mockMvc.perform(get("/caves/{cave-id}", caveId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(caveId.toString()))
                .andExpect(jsonPath("$.name").value("Cave"));

        // Then
        verify(caveService).findCaveById(caveId);
    }
    
    @Test
    void should_return_200_create_cave() throws Exception {
        // Given
        val caveId = UUID.randomUUID();
        when(caveService.createCave("Cave")).thenReturn(
                Cave.builder()
                        .id(caveId)
                        .name("Cave")
                        .build()
        );

        // When
        mockMvc.perform(post("/caves")
                        .param("cave-name", "Cave"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(caveId.toString()))
                .andExpect(jsonPath("$.name").value("Cave"));

        // Then
        verify(caveService).createCave("Cave");
    }
    
    @Test
    void should_return_200_update_cave() throws Exception {
        // Given
        val caveId = UUID.randomUUID();
        when(caveService.updateCave(caveId, "Cave")).thenReturn(
                Cave.builder()
                        .id(caveId)
                        .name("Cave")
                        .build()
        );

        // When
        mockMvc.perform(put("/caves/{cave-id}", caveId)
                        .param("cave-name", "Cave"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(caveId.toString()))
                .andExpect(jsonPath("$.name").value("Cave"));

        // Then
        verify(caveService).updateCave(caveId, "Cave");
    }
    
    @Test
    void should_return_200_delete_cave() throws Exception {
        // Given
        val caveId = UUID.randomUUID();

        // When
        mockMvc.perform(delete("/caves/{cave-id}", caveId))
                .andExpect(status().isOk());

        // Then
        verify(caveService).deleteCave(caveId);
    }
}