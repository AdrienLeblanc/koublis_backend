package com.koublis;

import com.koublis.entities.Wine;
import com.koublis.repository.WineRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(applicationClass);
    }

    private static Class<Application> applicationClass = Application.class;

    @Bean
    public CommandLineRunner init(WineRepository wineRepository) {
        return args -> {
            int i = 0;
            for (String name : Arrays.asList("Beuajolais", "Champagne", "Duloc", "Marais", "Fenon")) {
                Wine wine = new Wine(i, name);
                wineRepository.save(wine);
                i++;
            }
        };
    }
}