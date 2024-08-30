package com.linggash.spring_config_properties_learning.configurationproperties;

import com.linggash.spring_config_properties_learning.properties.ApplicationProperties;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = ConfigurationPropertiesTest.TestApplication.class)
public class ConfigurationPropertiesTest {

    @Autowired
    private ApplicationProperties properties;

    @Test
    void testConfigurationProperties() {
        Assertions.assertEquals("bambang", properties.getName());
        Assertions.assertEquals(1, properties.getVersion());
        Assertions.assertFalse(properties.isProductionMode());
    }

    @Test
    void testDatabaseProperties() {
        Assertions.assertEquals("bambang", properties.getDatabase().getUsername());
        Assertions.assertEquals("katakunci", properties.getDatabase().getPassword());
        Assertions.assertEquals("basisdata", properties.getDatabase().getDatabase());
        Assertions.assertEquals("jdbc:contoh", properties.getDatabase().getUrl());
    }

    @SpringBootApplication
    @EnableConfigurationProperties({
            ApplicationProperties.class
    })
    public static class TestApplication {

    }

}
