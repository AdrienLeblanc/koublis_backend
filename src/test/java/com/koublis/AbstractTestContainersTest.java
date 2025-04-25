package com.koublis;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class AbstractTestContainersTest {

    static final MySQLContainer<?> mySqlContainer = new MySQLContainer<>("mysql:lts");

    static {
        mySqlContainer.start();
    }

    @DynamicPropertySource
    static void configureMySQLProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mySqlContainer::getJdbcUrl);
        registry.add("spring.datasource.username", mySqlContainer::getUsername);
        registry.add("spring.datasource.password", mySqlContainer::getPassword);
        registry.add("spring.datasource.driver-class-name", mySqlContainer::getDriverClassName);
    }
}
