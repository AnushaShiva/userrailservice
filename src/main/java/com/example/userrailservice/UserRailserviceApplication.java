package com.example.userrailservice;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class UserRailserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserRailserviceApplication.class, args);
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	
	@Bean
	public Docket postsApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("RailWay USERSERVICE").apiInfo(apiInfo()).select()
				.paths(regex("/users.*")).build();
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("User-Service")
				.description("Sample Documentation Generateed Using SWAGGER2 for our RailWay USER SERVICE  Rest API")
				.termsOfServiceUrl("www.anu.com")
				.license("anusha")
				.licenseUrl("www.anu.com").version("1.0").build();
	}
	}
