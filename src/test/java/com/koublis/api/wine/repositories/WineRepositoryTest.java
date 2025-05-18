package com.koublis.api.wine.repositories;

import com.koublis.AbstractSpringTest;
import com.koublis.api.wine.domain.Cave;
import com.koublis.api.wine.domain.Wine;
import lombok.val;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WineRepositoryTest extends AbstractSpringTest {

    @Autowired
    private CaveRepository caveRepository;
    @Autowired
    private WineRepository wineRepository;

    private Cave cave;

    @BeforeEach
    void setUp() {
        cave = Cave.builder()
                .name("Test Cave")
                .build();
        caveRepository.save(cave);
    }

    @AfterEach
    void tearDown() {
        caveRepository.deleteAll();
        wineRepository.deleteAll();
    }

    @Test
    void should_save_wine() {
        // GIVEN
        val wine = Wine.builder()
                .cave(cave)
                .name("Test Wine")
                .build();

        // WHEN
        val savedWine = wineRepository.save(wine);

        // THEN
        assertThat(savedWine.getId()).isNotNull();
        assertThat(savedWine.getId().version()).isEqualTo(4);
        assertThat(savedWine.getName()).isEqualTo(wine.getName());
    }

    @Test
    void should_find_wine_by_id() {
        // GIVEN
        val wine = Wine.builder()
                .cave(cave)
                .name("Test Wine")
                .build();
        val savedWine = wineRepository.save(wine);

        // WHEN
        val wineFound = wineRepository.findById(savedWine.getId()).orElse(null);

        // THEN
        assertThat(wineFound).isNotNull();
        assertThat(wineFound.getId()).isEqualTo(savedWine.getId());
        assertThat(wineFound.getName()).isEqualTo(savedWine.getName());
    }

    @Test
    void should_fail_to_save_wine_without_cave() {
        // GIVEN
        val wine = Wine.builder()
                .name("Test Wine")
                .build();

        // WHEN
        val exception = assertThatThrownBy(() -> wineRepository.save(wine));

        // THEN
        exception.isInstanceOf(DataIntegrityViolationException.class);
    }

}