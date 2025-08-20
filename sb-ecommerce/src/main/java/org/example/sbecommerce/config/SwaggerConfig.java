package org.example.sbecommerce.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        SecurityScheme bearerScheme = new SecurityScheme().type(SecurityScheme.Type.HTTP)
                .scheme("bearer").
                bearerFormat("JWT").
                description("JWT Bearer Token");

        SecurityRequirement bearerRequirement = new SecurityRequirement().addList("Bearer Authentication");

        Info apiInfo = new Info()
                .title("SpringBoot Ecommerce Project")
                .description("OpenAPI documentation for Spring Boot Ecommerce Project.")
                .version("v1.0.0")
                .contact(new Contact()
                        .name("Nikhil Chinchore")
                        .email("nikhilchinchore2002@gmail.com")
                        .url("https://github.com/NI-TECH-hub"));



        List<Tag> tags = List.of(
                new Tag().name("Auth APIs").description("Authentication and user account endpoints"),
                new Tag().name("Category APIs").description("APIs for managing categories"),
                new Tag().name("Product APIs").description("APIs for managing products"),
                new Tag().name("Cart APIs").description("APIs for managing carts and items"),
                new Tag().name("Order APIs").description("APIs for placing and managing orders"),
                new Tag().name("Address APIs").description("APIs for managing user addresses")
        );

        return new OpenAPI()
                .info(apiInfo)
                .components(new Components().addSecuritySchemes("Bearer Authentication", bearerScheme))
                .addSecurityItem(bearerRequirement)
                .tags(tags);
    }
}

