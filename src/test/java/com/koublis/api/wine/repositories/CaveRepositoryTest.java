package com.koublis.api.wine.repositories;

import com.koublis.AbstractSpringTest;
import com.koublis.api.wine.domain.Cave;
import com.koublis.api.wine.domain.Wine;
import lombok.val;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CaveRepositoryTest extends AbstractSpringTest {

    @Autowired
    private CaveRepository caveRepository;
    @Autowired
    private WineRepository wineRepository;

    @AfterEach
    void tearDown() {
        caveRepository.deleteAll();
    }

    @Test
    void should_save_cave_in_mongodb() {
        // Given
        val cave = Cave.builder()
                .name("Test Cave")
                .build();

        // When
        val savedCave = caveRepository.save(cave);

        // Then
        assertThat(savedCave.getId()).isNotNull();
        assertThat(savedCave.getName()).isEqualTo(cave.getName());
    }

    @Test
    void should_find_cave_by_id() {
        // Given
        val cave = Cave.builder()
                .name("Test Cave")
                .build();
        val savedCave = caveRepository.save(cave);

        // When
        val foundCave = caveRepository.findById(savedCave.getId()).orElse(null);

        // Then
        assertThat(foundCave).isNotNull();
        assertThat(foundCave.getId()).isEqualTo(savedCave.getId());
        assertThat(foundCave.getName()).isEqualTo(savedCave.getName());
    }

    @Test
    void should_save_cave_with_wines() {
        // Given

        val cave = Cave.builder()
                .name("Test Cave")
                .build();

        val savedCave = caveRepository.save(cave);

        val wine1 = Wine.builder()
                .cave(savedCave)
                .appellation("Wine1")
                .build();

        val wine2 = Wine.builder()
                .cave(savedCave)
                .appellation("Wine2")
                .build();

        // When
        caveRepository.save(savedCave.toBuilder()
                .wines(List.of(wine1, wine2))
                .build());

        val caveFound = caveRepository.findById(savedCave.getId()).orElse(null);
        val winesFound = wineRepository.findAllByCaveId(savedCave.getId());

        // Then
        assertThat(savedCave.getId()).isNotNull();
        assertThat(savedCave.getName()).isEqualTo(cave.getName());

        assertThat(winesFound)
                .isNotEmpty()
                .hasSize(2);

        assertThat(caveFound).isNotNull();
        assertThat(caveFound.getWines())
                .isNotEmpty()
                .hasSize(2)
                .extracting(Wine::getAppellation)
                .containsExactlyInAnyOrder("Wine1", "Wine2");

    }

}