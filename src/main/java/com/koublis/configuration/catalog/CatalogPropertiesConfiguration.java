package com.koublis.configuration.catalog;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "koublis.catalog")
public class CatalogPropertiesConfiguration {
    private String location;
}
