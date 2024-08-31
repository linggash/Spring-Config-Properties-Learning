package com.linggash.spring_config_properties_learning.configurationproperties;

import com.linggash.spring_config_properties_learning.converter.StringToDateConverter;
import com.linggash.spring_config_properties_learning.properties.ApplicationProperties;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.convert.ApplicationConversionService;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.core.convert.ConversionService;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Arrays;
import java.util.Date;

@SpringBootTest(classes = ConfigurationPropertiesTest.TestApplication.class)
public class ConfigurationPropertiesTest {

    @Autowired
    private ApplicationProperties properties;

    @Autowired
    private ConversionService conversionService;

    @Test
    void testConversionService() {
        Assertions.assertTrue(conversionService.canConvert(String.class, Duration.class));
        Assertions.assertTrue(conversionService.canConvert(String.class, Date.class));

        Duration result = conversionService.convert("10s", Duration.class);
        Assertions.assertEquals(Duration.ofSeconds(10), result);
    }

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

    @Test
    void testCollection() {
        Assertions.assertEquals(Arrays.asList("produk", "pelanggan", "kategori"), properties.getDatabase().getWhiteListTables());
        Assertions.assertEquals(100, properties.getDatabase().getMaxTablesSize().get("produk"));
        Assertions.assertEquals(100, properties.getDatabase().getMaxTablesSize().get("pelanggan"));
        Assertions.assertEquals(100, properties.getDatabase().getMaxTablesSize().get("kategori"));
    }

    @Test
    void testEmbeddedCollection() {
        Assertions.assertEquals("default", properties.getDefaultRoles().get(0).getId());
        Assertions.assertEquals("Default Role", properties.getDefaultRoles().get(0).getName());
        Assertions.assertEquals("default", properties.getDefaultRoles().get(1).getId());
        Assertions.assertEquals("Default Role", properties.getDefaultRoles().get(1).getName());

        Assertions.assertEquals("satu", properties.getRoles().get("satu").getId());
        Assertions.assertEquals("utas", properties.getRoles().get("satu").getName());
        Assertions.assertEquals("dua", properties.getRoles().get("dua").getId());
        Assertions.assertEquals("uad", properties.getRoles().get("dua").getName());
    }

    @Test
    void testDuration() {
        Assertions.assertEquals(Duration.ofSeconds(10), properties.getDefaultTimeout());
    }

    @Test
    void testConverter() {
        Date expireDate = properties.getExpireDate();

        var dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Assertions.assertEquals("2024-08-22", dateFormat.format(expireDate));
    }

    @SpringBootApplication
    @EnableConfigurationProperties({
            ApplicationProperties.class
    })
    @Import(StringToDateConverter.class)
    public static class TestApplication {

        @Bean
        public ConversionService conversionService(StringToDateConverter stringToDateConverter) {
            ApplicationConversionService conversionService = new ApplicationConversionService();
            conversionService.addConverter(stringToDateConverter);
            return conversionService;
        }

    }

}
