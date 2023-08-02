/*
 * package com.example.userrailservice;
 * 
 * import static springfox.documentation.builders.PathSelectors.regex;
 * 
 * import org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration;
 * 
 * import springfox.documentation.builders.ApiInfoBuilder; import
 * springfox.documentation.service.ApiInfo; import
 * springfox.documentation.spi.DocumentationType; import
 * springfox.documentation.spring.web.plugins.Docket; import
 * springfox.documentation.swagger2.annotations.EnableSwagger2;
 * 
 * @Configuration
 * 
 * @EnableSwagger2 public class SwaggerConfig {
 * 
 * @Bean public Docket postsApi() { return new
 * Docket(DocumentationType.SWAGGER_2).groupName("Java Techie").apiInfo(apiInfo(
 * )).select() .paths(regex("/users.*")).build(); }
 * 
 * 
 * ApiInfo apiInfo() { return new
 * ApiInfoBuilder().title("User REST CRUD operations API in Spring-Boot 2")
 * .description("Sample REST API for monitoring using Spring Boot, Prometheus and Graphana "
 * ) .termsOfServiceUrl("").version("0.0.1-SNAPSHOT").contact(new
 * Contact("Satish Sharma", "https://github.com/hellosatish/monitoring/person",
 * "https://github.com/hellosatish")) .build(); }
 * 
 * 
 * private ApiInfo apiInfo() { return new ApiInfoBuilder().title("User-Service")
 * .description("Sample Documentation Generateed Using SWAGGER2 for our Book Rest API"
 * ) .termsOfServiceUrl(
 * "https://www.youtube.com/channel/UCORuRdpN2QTCKnsuEaeK-kQ")
 * .license("Java_Gyan_Mantra License")
 * .licenseUrl("https://www.youtube.com/channel/UCORuRdpN2QTCKnsuEaeK-kQ").
 * version("1.0").build(); }
 * 
 * 
 * @Bean public Docket configureControllerPackageAndConvertors() { return new
 * Docket(DocumentationType.SWAGGER_2).select()
 * .apis(RequestHandlerSelectors.basePackage("com.satish.monitoring")).build().
 * apiInfo(apiInfo()); }
 * 
 * }
 */