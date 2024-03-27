package com.niqactivate.niqactivateapi.configs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ForwardedHeaderFilter;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

public class SwaggerConfiguration {
    @Configuration
    @OpenAPIDefinition(info = @Info(title = "NIQ Activate API", description = "NIQ Activate API", version = "v1", license = @License(name = "NIQActivate", url = "http://localhost:8080")))
    public static class SwaggerConfig {
        @Bean
        public OpenAPI customOpenAPI() {
            return new OpenAPI();


        }

        @Bean
        ForwardedHeaderFilter forwardedHeaderFilter() {
            return new ForwardedHeaderFilter();
        }
    }
}
