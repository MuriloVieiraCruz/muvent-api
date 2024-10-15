package com.muvent.api.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Murilo Vieira Cruz",
                        email = "contactMurilo@gmail.com",
                        url = "https://murilocoding.com"
                ),
                description = "OpenApi documentation for Spring",
                version = "1.0",
                license = @License(
                        name = "MIT Licence",
                        url = "https://licence-url.com"
                ),
                termsOfService = "Terms of service"
        ),
        servers = {
                @Server(
                        description = "Local Environment",
                        url = "http://localhost:8081"
                ),
                @Server(
                        description = "Test Environment",
                        url = "http://localhost:8080"
                )
        }
)
public class OpenApiConfig {
}
