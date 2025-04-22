package com.koublis.configuration.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.convert.DurationUnit;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "koublis.security")
public class SecurityPropertiesConfiguration {
    private String jwtSecret;

    @DurationUnit(ChronoUnit.HOURS)
    private Duration jwtExpiration;
}
