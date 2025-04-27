package com.koublis.configuration;

import com.koublis.api.wine.mappers.CaveMapper;
import com.koublis.api.wine.mappers.WineMapper;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class MapperTestConfiguration {

    @Bean
    public WineMapper wipeMapper() {
        return new WineMapper();
    }

    @Bean
    public CaveMapper caveMapper(WineMapper wipeMapper) {
        return new CaveMapper(wipeMapper);
    }
}
