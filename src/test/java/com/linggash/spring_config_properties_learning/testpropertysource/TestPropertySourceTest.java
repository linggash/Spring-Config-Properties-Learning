package com.linggash.spring_config_properties_learning.testpropertysource;

import lombok.Getter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Component;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.TestPropertySources;

/*
* TestPropertySource/s digunakan seperti PropertySource
* tetapi ini dikhuuskan untuk unit test karena kita bisa
* membuat properties sendiri di folder test
* */
@TestPropertySources({
        @TestPropertySource("classpath:/test.properties")
})
@SpringBootTest(classes = TestPropertySourceTest.TestApplication.class)
public class TestPropertySourceTest {

    @Autowired
    private TestApplication.SampleProperties properties;

    @Test
    void testPropertyResource() {
        Assertions.assertEquals("Sample Test Project", properties.getName());
        Assertions.assertEquals(2, properties.getVersion());
    }

    @SpringBootApplication
    public static class TestApplication {

        @Component
        @Getter
        public static class SampleProperties {

            @Value("${sample.name}")
            private String name;

            @Value("${sample.version}")
            private Integer version;

        }

    }

}
