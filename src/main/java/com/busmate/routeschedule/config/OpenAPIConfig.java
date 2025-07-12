package com.busmate.routeschedule.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Bus Routes & Schedules Microservice")
                        .version("1.0")
                        .description("Microservice for managing routes and schedules in Smart Bus System"));
    }
}

