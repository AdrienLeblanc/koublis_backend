package com.koublis;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("it")
public abstract class AbstractSpringIntegrationTest extends AbstractTestContainersTest {

    @LocalServerPort
    protected String localServerPort;

    @BeforeEach
    void setUp() {
        System.setProperty("karate.server.port", String.valueOf(localServerPort));
    }
}
