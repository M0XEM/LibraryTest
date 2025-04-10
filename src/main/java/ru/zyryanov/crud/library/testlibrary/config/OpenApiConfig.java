package ru.zyryanov.crud.library.testlibrary.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI libraryOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Rest API")
                        .description("Тестовое задание: REST API для библиотеки")
                        .contact(new Contact()
                                .name("Макс")
                                .email("zekov-z@mail.ru")
                        )
                );
    }
}
