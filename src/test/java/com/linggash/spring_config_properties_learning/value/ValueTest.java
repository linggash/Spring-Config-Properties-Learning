package com.linggash.spring_config_properties_learning.value;

import lombok.Getter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;


@SpringBootTest(classes = ValueTest.TestApplication.class)
public class ValueTest {

    @Autowired
    private TestApplication.ApplicationProperties applicationProperties;

    @Test
    void valueTest() {
        Assertions.assertEquals("spring-config-properties-learning", applicationProperties.getName());
        Assertions.assertEquals(1, applicationProperties.getVersion());
        Assertions.assertFalse(applicationProperties.isProductionMode);
    }

    @SpringBootApplication
    public static class TestApplication{

        @Component
        @Getter
        public static class ApplicationProperties {

            /*
            @Value dapat digunakan untuk mengambil data yang ada di
            application.properties dan sudah langsung dikonversikan datanya.
            Selain itu kita juga bisa mengambil environment variable menggunakan
            annotation ini.
            */
            @Value("${spring.application.name}")
            private String name;

            @Value("${application.version}")
            private Integer version;

            @Value("${application.production-mode}")
            private boolean isProductionMode;

        }

    }

}
