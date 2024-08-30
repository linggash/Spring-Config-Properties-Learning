package com.linggash.spring_config_properties_learning.messagesource;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.Locale;

@SpringBootTest(classes = MessageSourceTest.TestApplication.class)
public class MessageSourceTest {

    @Autowired
    private MessageSource messageSource;

    @Test
    void testDefaultLocale() {
        String message = messageSource.getMessage("hello", new Object[]{"Bambang"}, Locale.ENGLISH);
        Assertions.assertEquals("Hello Bambang", message);
    }

    @Test
    void testIndonesianLocale() {
        String message = messageSource.getMessage("hello", new Object[]{"Bambang"}, new Locale("in_ID"));
        Assertions.assertEquals("Halo Bambang", message);
    }

    @SpringBootApplication
    public static class TestApplication {

        @Bean
        public MessageSource messageSource() {
            ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
            messageSource.setBasenames("my");
            return messageSource;
        }

    }

}
