package com.koublis.api.catalog.repositories;

import com.koublis.AbstractSpringTest;
import com.koublis.api.catalog.domain.CatalogWine;
import lombok.val;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CatalogWineRepositoryTest extends AbstractSpringTest {

    @Autowired
    private CatalogWineRepository catalogWineRepository;

    @AfterEach
    void tearDown() {
        catalogWineRepository.deleteAll();
    }

    @Test
    void should_save_catalog_wine() {
        // Given
        val catalogWine = CatalogWine.builder()
                .title("Test Wine")
                .description("Test Description")
                .build();

        // When
        val savedCatalogWine = catalogWineRepository.save(catalogWine);

        // Then
        assertThat(savedCatalogWine.getId()).isNotNull();
        assertThat(savedCatalogWine.getId().version()).isEqualTo(4);
        assertThat(savedCatalogWine.getTitle()).isEqualTo(catalogWine.getTitle());
        assertThat(savedCatalogWine.getDescription()).isEqualTo(catalogWine.getDescription());
    }
    
    @Test
    void should_search_catalog_wine_by_title() {
        // Given
        val catalogWine1 = CatalogWine.builder()
                .title("Saint-Emilion")
                .description("French red wine")
                .build();
        val catalogWine2 = CatalogWine.builder()
                .title("Bourgogne")
                .description("French white wine")
                .build();
        catalogWineRepository.saveAll(List.of(catalogWine1, catalogWine2));

        // When
        val result = catalogWineRepository.search("Bourgogne", null);

        // Then
        assertThat(result).hasSize(1);
    }
    
    @Test
    void should_search_catalog_wine_by_taster_name() {
        // Given
        val catalogWine1 = CatalogWine.builder()
                .title("Saint-Emilion")
                .description("French red wine")
                .tasterName("John Doe")
                .build();
        val catalogWine2 = CatalogWine.builder()
                .title("Bourgogne")
                .description("French white wine")
                .tasterName("Jane Doe")
                .build();
        catalogWineRepository.saveAll(List.of(catalogWine1, catalogWine2));

        // When
        val result = catalogWineRepository.search("John", null);

        // Then
        assertThat(result).hasSize(1);
    }
    
    @Test
    void should_search_catalog_wine_by_taster_twitter_handle() {
        // Given
        val catalogWine1 = CatalogWine.builder()
                .title("Saint-Emilion")
                .description("French red wine")
                .tasterTwitterHandle("@johndoe")
                .build();
        val catalogWine2 = CatalogWine.builder()
                .title("Bourgogne")
                .description("French white wine")
                .tasterTwitterHandle("@janedoe")
                .build();
        catalogWineRepository.saveAll(List.of(catalogWine1, catalogWine2));

        // When
        val result = catalogWineRepository.search("@johndoe", null);

        // Then
        assertThat(result).hasSize(1);
    }
    
    @Test
    void should_search_catalog_wine_by_designation() {
        // Given
        val catalogWine1 = CatalogWine.builder()
                .title("Saint-Emilion")
                .description("French red wine")
                .designation("Grand Cru")
                .build();
        val catalogWine2 = CatalogWine.builder()
                .title("Bourgogne")
                .description("French white wine")
                .designation("Premier Cru")
                .build();
        catalogWineRepository.saveAll(List.of(catalogWine1, catalogWine2));

        // When
        val result = catalogWineRepository.search("Grand Cru", null);

        // Then
        assertThat(result).hasSize(1);
    }
    
    @Test
    void should_search_catalog_wine_by_variety() {
        // Given
        val catalogWine1 = CatalogWine.builder()
                .title("Saint-Emilion")
                .description("French red wine")
                .variety("Merlot")
                .build();
        val catalogWine2 = CatalogWine.builder()
                .title("Bourgogne")
                .description("French white wine")
                .variety("Chardonnay")
                .build();
        catalogWineRepository.saveAll(List.of(catalogWine1, catalogWine2));

        // When
        val result = catalogWineRepository.search("Merlot", null);

        // Then
        assertThat(result).hasSize(1);
    }
    
    @Test
    void should_search_catalog_wine_by_region1() {
        // Given
        val catalogWine1 = CatalogWine.builder()
                .title("Saint-Emilion")
                .description("French red wine")
                .region1("Bordeaux")
                .build();
        val catalogWine2 = CatalogWine.builder()
                .title("Bourgogne")
                .description("French white wine")
                .region1("Burgundy")
                .build();
        catalogWineRepository.saveAll(List.of(catalogWine1, catalogWine2));

        // When
        val result = catalogWineRepository.search("Bordeaux", null);

        // Then
        assertThat(result).hasSize(1);
    }
    
    @Test
    void should_search_catalog_wine_by_region2() {
        // Given
        val catalogWine1 = CatalogWine.builder()
                .title("Saint-Emilion")
                .description("French red wine")
                .region2("Libournais")
                .build();
        val catalogWine2 = CatalogWine.builder()
                .title("Bourgogne")
                .description("French white wine")
                .region2("Côte d'Or")
                .build();
        catalogWineRepository.saveAll(List.of(catalogWine1, catalogWine2));

        // When
        val result = catalogWineRepository.search("Libournais", null);

        // Then
        assertThat(result).hasSize(1);
    }
    
    @Test
    void should_search_catalog_wine_by_province() {
        // Given
        val catalogWine1 = CatalogWine.builder()
                .title("Saint-Emilion")
                .description("French red wine")
                .province("Gironde")
                .build();
        val catalogWine2 = CatalogWine.builder()
                .title("Bourgogne")
                .description("French white wine")
                .province("Côte d'Or")
                .build();
        catalogWineRepository.saveAll(List.of(catalogWine1, catalogWine2));

        // When
        val result = catalogWineRepository.search("Gironde", null);

        // Then
        assertThat(result).hasSize(1);
    }
    
    @Test
    void should_search_catalog_wine_by_country() {
        // Given
        val catalogWine1 = CatalogWine.builder()
                .title("Saint-Emilion")
                .description("French red wine")
                .country("France")
                .build();
        val catalogWine2 = CatalogWine.builder()
                .title("Bourgogne")
                .description("French white wine")
                .country("France")
                .build();
        catalogWineRepository.saveAll(List.of(catalogWine1, catalogWine2));

        // When
        val result = catalogWineRepository.search("France", null);

        // Then
        assertThat(result).hasSize(2);
    }
    
    @Test
    void should_search_catalog_wine_by_winery() {
        // Given
        val catalogWine1 = CatalogWine.builder()
                .title("Saint-Emilion")
                .description("French red wine")
                .winery("Château Cheval Blanc")
                .build();
        val catalogWine2 = CatalogWine.builder()
                .title("Bourgogne")
                .description("French white wine")
                .winery("Domaine de la Romanée-Conti")
                .build();
        catalogWineRepository.saveAll(List.of(catalogWine1, catalogWine2));

        // When
        val result = catalogWineRepository.search("Château Cheval Blanc", null);

        // Then
        assertThat(result).hasSize(1);
    }
    
    @Test
    void should_search_catalog_wine_by_multiple_fields() {
        // Given
        val catalogWine1 = CatalogWine.builder()
                .title("Saint-Emilion")
                .description("French red wine")
                .tasterName("John Doe")
                .tasterTwitterHandle("@johndoe")
                .designation("Grand Cru")
                .variety("Merlot")
                .region1("Bordeaux")
                .region2("Libournais")
                .province("Gironde")
                .country("France")
                .winery("Château Cheval Blanc")
                .build();
        val catalogWine2 = CatalogWine.builder()
                .title("Bourgogne")
                .description("French white wine")
                .tasterName("Jane Doe")
                .tasterTwitterHandle("@janedoe")
                .designation("Premier Cru")
                .variety("Chardonnay")
                .region1("Burgundy")
                .region2("Côte d'Or")
                .province("Côte d'Or")
                .country("France")
                .winery("Domaine de la Romanée-Conti")
                .build();
        catalogWineRepository.saveAll(List.of(catalogWine1, catalogWine2));

        // When
        val result = catalogWineRepository.search("ou", null);

        // Then
        assertThat(result).hasSize(2);
    }
    
    @Test
    void should_search_catalog_wine_by_empty_query() {
        // Given
        val catalogWine1 = CatalogWine.builder()
                .title("Saint-Emilion")
                .description("French red wine")
                .build();
        val catalogWine2 = CatalogWine.builder()
                .title("Bourgogne")
                .description("French white wine")
                .build();
        catalogWineRepository.saveAll(List.of(catalogWine1, catalogWine2));

        // When
        val result = catalogWineRepository.search("", null);

        // Then
        assertThat(result).hasSize(2);
    }
    
    @Test
    void should_search_catalog_wine_by_null_query() {
        // Given
        val catalogWine1 = CatalogWine.builder()
                .title("Saint-Emilion")
                .description("French red wine")
                .build();
        val catalogWine2 = CatalogWine.builder()
                .title("Bourgogne")
                .description("French white wine")
                .build();
        catalogWineRepository.saveAll(List.of(catalogWine1, catalogWine2));

        // When
        val result = catalogWineRepository.search(null, null);

        // Then
        assertThat(result).hasSize(2);
    }
}