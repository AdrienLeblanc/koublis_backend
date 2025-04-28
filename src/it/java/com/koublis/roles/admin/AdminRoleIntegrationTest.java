package com.koublis.roles.admin;


import com.intuit.karate.junit5.Karate;
import com.koublis.AbstractSpringIntegrationTest;

class AdminRoleIntegrationTest extends AbstractSpringIntegrationTest {

    @Karate.Test
    Karate adminRoleSecurityIT() {
        return Karate.run().relativeTo(getClass());
    }

}