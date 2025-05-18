package com.koublis.api.catalog.services;

import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.koublis.api.catalog.domain.CatalogWine;
import com.koublis.api.catalog.repositories.ECatalogWineRepository;
import com.koublis.configuration.catalog.CatalogPropertiesConfiguration;
import com.koublis.configuration.exceptions.TechnicalException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

import static com.koublis.configuration.exceptions.Reason.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class CatalogWineInitializer implements ApplicationRunner {

    private final ObjectMapper objectMapper;
    private final ECatalogWineRepository eCatalogWineRepository;
    private final CatalogPropertiesConfiguration catalogPropertiesConfiguration;
    private final ElasticsearchECatalogWineService elasticsearchECatalogWineService;

    @Override
    public void run(ApplicationArguments args) {
        populateCatalogWines();
    }

    private void populateCatalogWines() {
        if (eCatalogWineRepository.count() > 0) {
            log.info("Catalog has already been populated, skipping initialization.");
            return;
        }

        log.info("Populating catalog with wines from file: {}", catalogPropertiesConfiguration.getLocation());
        try (val catalogInputStream = getInputStream();
             val parser = objectMapper.getFactory().createParser(catalogInputStream)) {

            if (parser.nextToken() != JsonToken.START_ARRAY) {
                throw new TechnicalException(INVALID_JSON_FILE_START);
            }

            var index = 0;
            while (parser.nextToken() != JsonToken.END_ARRAY) {
                val catalogWine = objectMapper.readValue(parser, CatalogWine.class);

                log.debug("Saving wine n°{} in catalog with name={}", ++index, catalogWine.getTitle());
                eCatalogWineRepository.save(catalogWine);
            }

            log.info("Catalog populated with {} wines.", index);

        } catch (IOException ex) {
            throw new TechnicalException(FAILED_TO_PARSE_CATALOG_WINES, ex);
        } finally {
            elasticsearchECatalogWineService.reindex();
        }

    }

    private InputStream getInputStream() {
        try {
            return new ClassPathResource(catalogPropertiesConfiguration.getLocation()).getInputStream();
        } catch (Exception ex) {
            throw new TechnicalException(FAILED_TO_LOAD_CATALOG_WINES, ex);
        }
    }
}
