package com.koublis.catalog;


import com.intuit.karate.junit5.Karate;
import com.koublis.AbstractSpringIntegrationTest;

class CatalogWinesIntegrationTest extends AbstractSpringIntegrationTest {

    @Karate.Test
    Karate testCatalog() {
        return Karate.run().relativeTo(getClass());
    }

}