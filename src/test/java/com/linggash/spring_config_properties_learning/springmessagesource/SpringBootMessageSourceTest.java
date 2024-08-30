package com.linggash.spring_config_properties_learning.springmessagesource;

import lombok.Setter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.stereotype.Component;

import java.util.Locale;

@SpringBootTest
public class SpringBootMessageSourceTest {

    @Autowired
    private TestApplication.SampleSource sampleSource;

    @Test
    void testHelloBambang() {
        Assertions.assertEquals("Hello Bambang", sampleSource.helloBambang(Locale.ENGLISH));
        Assertions.assertEquals("Halo Bambang", sampleSource.helloBambang(new Locale("in_ID")));
    }

    @SpringBootApplication
    public static class TestApplication {

        @Component
        public static class SampleSource implements MessageSourceAware {

            @Setter
            private MessageSource messageSource;

            public String helloBambang(Locale locale){
                return messageSource.getMessage("hello", new Object[]{"Bambang"}, locale);
            }

        }

    }

}
