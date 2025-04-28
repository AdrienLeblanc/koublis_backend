package com.koublis.roles.moderator;


import com.intuit.karate.junit5.Karate;
import com.koublis.AbstractSpringIntegrationTest;

class ModeratorRoleIntegrationTest extends AbstractSpringIntegrationTest {

    @Karate.Test
    Karate moderatorRoleSecurityIT() {
        return Karate.run().relativeTo(getClass());
    }

}