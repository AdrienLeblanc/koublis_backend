package com.koublis;

import com.koublis.configuration.MapperTestConfiguration;
import com.koublis.configuration.SecurityTestConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@Import(value = {
        MapperTestConfiguration.class,
        SecurityTestConfiguration.class
})
public abstract class AbstractControllerTest {
}
