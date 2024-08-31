package com.linggash.spring_config_properties_learning;

import com.linggash.spring_config_properties_learning.converter.StringToDateConverter;
import com.linggash.spring_config_properties_learning.properties.ApplicationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.convert.ApplicationConversionService;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.ConversionService;

@SpringBootApplication
@EnableConfigurationProperties({
		ApplicationProperties.class
})
public class SpringConfigPropertiesLearningApplication {

	@Bean
	public ConversionService conversionService(StringToDateConverter stringToDateConverter) {
		ApplicationConversionService conversionService = new ApplicationConversionService();
		conversionService.addConverter(stringToDateConverter);
		return conversionService;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringConfigPropertiesLearningApplication.class, args);
	}

}
