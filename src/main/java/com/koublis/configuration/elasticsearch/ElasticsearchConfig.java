package com.koublis.configuration.elasticsearch;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
import org.springframework.lang.NonNull;

import java.time.Duration;

@Configuration
@RequiredArgsConstructor
public class ElasticsearchConfig extends ElasticsearchConfiguration {

    private final ElasticsearchProperties elasticsearchProperties;

    @Override
    @NonNull
    public ClientConfiguration clientConfiguration() {
        val builder = ClientConfiguration.builder()
                .connectedTo(elasticsearchProperties.getUris().toArray(String[]::new))
                .withConnectTimeout(Duration.ofSeconds(5))
                .withSocketTimeout(Duration.ofSeconds(5));

        if (elasticsearchProperties.getUsername() != null && elasticsearchProperties.getPassword() != null) {
            return builder.withBasicAuth(
                    elasticsearchProperties.getUsername(),
                    elasticsearchProperties.getPassword()
            ).build();
        }

        return builder.build();
    }
}
