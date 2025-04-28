package com.koublis.roles.user;


import com.intuit.karate.junit5.Karate;
import com.koublis.AbstractSpringIntegrationTest;

class UserRoleIntegrationTest extends AbstractSpringIntegrationTest {

    @Karate.Test
    Karate userRoleSecurityIT() {
        return Karate.run().relativeTo(getClass());
    }

}