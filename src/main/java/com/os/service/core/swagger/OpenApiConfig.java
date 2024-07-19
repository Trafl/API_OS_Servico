package com.os.service.core.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ProblemDetail;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Ordens e Serviços")
                        .version("v1")
                        .description("Documento para auxiliar no manuseio da API"))
                .components(new Components()
                        .addSchemas("ProblemDetail", new Schema()
                                .type("object")
                                .addProperty("type", new StringSchema().example("https://api/ordem_servico/errors/entity-not-found"))
                                .addProperty("title", new StringSchema().example("Order not found"))
                                .addProperty("status", new StringSchema().example(404))
                                .addProperty("detail", new StringSchema().example("Order id 5 was not found"))
                                .addProperty("timestamp", new StringSchema().example("2024-08-16sT19:32:54.253417400Z"))
                        ));
    }
}
