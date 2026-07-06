package com.alexander.teamwork_api.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    // Configures Swagger to use JWT authentication.
    @Bean
    public OpenAPI openAPI() {

        String securityScheme = "Bearer Authentication";

        return new OpenAPI()

                .info(new Info()
                        .title("Teamwork API")
                        .version("1.0")
                        .description("Employee Collaboration API"))

                .addSecurityItem(
                        new SecurityRequirement()
                                .addList(securityScheme)
                )

                .components(
                        new Components()
                                .addSecuritySchemes(
                                        securityScheme,
                                        new SecurityScheme()
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer")
                                                .bearerFormat("JWT")
                                )
                );
    }

}