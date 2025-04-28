package com.koublis.wines;


import com.intuit.karate.junit5.Karate;
import com.koublis.AbstractSpringIntegrationTest;

class WinesIntegrationTest extends AbstractSpringIntegrationTest {

    @Karate.Test
    Karate winesIT() {
        return Karate.run().relativeTo(getClass());
    }

}