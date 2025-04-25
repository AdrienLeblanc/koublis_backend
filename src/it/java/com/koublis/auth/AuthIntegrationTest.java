package com.koublis.auth;


import com.intuit.karate.junit5.Karate;
import com.koublis.AbstractSpringIntegrationTest;

class AuthIntegrationTest extends AbstractSpringIntegrationTest {

    @Karate.Test
    Karate testAuth() {
        return Karate.run().relativeTo(getClass());
    }

}