package com.openclassrooms.api_chatop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfiguration {

    /**
     * This method returns an instance of OpenAPI.
     *
     * @return The OpenAPI instance
     */
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().addSecurityItem(new SecurityRequirement().addList("JWT Authentication"))
                .components(new Components().addSecuritySchemes("JWT Authentication", createAPIKeyScheme()))
                .info(new Info().title("ChatOp REST API")
                        .description("Documentation de l'API Rest de l'application ChatOp.")
                        .version("1.0").contact(new Contact().name("Antoine Chemin")));
    }

    /**
     * Creates a security scheme for API key authentication.
     *
     * @return The created security scheme
     */
    private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme().type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .scheme("");
    }
}