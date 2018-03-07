package com.demo.scrabble.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * @author Vijay Kumar S
 * @description: Config File for Swagger
 * Copyright (c) Demo VJ 2017
 * Created Date 10/14/2017
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Configuration for Swagger API.
     *
     * @return
     */
    @Bean
    public Docket scrabbleApi() {
        logger.info("Entering into COnfig ::");
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.demo.scrabble.controllers"))
                .paths(regex("/.*"))
                .build()
                .apiInfo(metaData());
    }

    /**
     * Meta Data defination which will be displayed in Swagger API
     *
     * @return
     */
    private ApiInfo metaData() {
        logger.info("Entering into MetaData ::");
        ApiInfo apiInfo = new ApiInfo(
                "Scrabble-Solver-Service",
                "Scrabble Solver Service Designed and Developed by Vj for Demo VJ",
                "1.0",
                "Terms of service",
                new Contact("Vijay Kumar S", "", "aitvijay@gmail.com"),
                "Demo VJ",
                "https://www.apache.org/licenses/LICENSE-2.0");
        return apiInfo;
    }
}
