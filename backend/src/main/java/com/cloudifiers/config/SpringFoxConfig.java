package com.cloudifiers.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/*
 * Sources:
 * https://www.baeldung.com/swagger-2-documentation-for-spring-rest-api
 * https://dzone.com/articles/spring-boot-restful-api-documentation-with-swagger
 * 
 * URL:
 * http://localhost:8080/swagger-ui.html
 */
@Configuration
@EnableSwagger2
public class SpringFoxConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build().apiInfo(metaData());
	}

	private ApiInfo metaData() {
		ApiInfo apiInfo = new ApiInfo("Cloudifiers", "A social networking application", "1.0", "Terms of service",
				new Contact("Jay Patel", "https://www.cloudifiers.com", "contact@cloudifiers.com"), "", "",
				Collections.emptyList());
		return apiInfo;
	}
}
