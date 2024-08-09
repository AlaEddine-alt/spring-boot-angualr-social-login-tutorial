package com.demo.social.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

import static io.swagger.v3.oas.annotations.enums.SecuritySchemeType.HTTP;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Oussema sahbeni",
                        email = "oussemasahbeni300@gmail.com",
                        url = "https://oussemasahbeni.tn"
                ),
                description = "OpenApi documentation for social-login-demo",
                title = "OpenApi specification",
                version = "1.0",
                termsOfService = "Terms of service"
        ),
        servers = {
                @Server(
                        description = "Local ENV",
                        url = "http://localhost:9000/api/v1"
                ),
        },
        security = {
                @SecurityRequirement(
                        name = "bearerAuth"

                )
        }
)
@SecurityScheme(
        name = "bearerAuth",
        description = "JWT auth description",
        scheme = "bearer",
        type = HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
}
