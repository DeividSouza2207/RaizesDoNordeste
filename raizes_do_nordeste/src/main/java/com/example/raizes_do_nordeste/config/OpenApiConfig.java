package com.example.raizes_do_nordeste.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@Configuration
@OpenAPIDefinition(
	info = @Info(
			title = "Raízes do Nordeste",
			version = "1.0",
			description = "API para gerenciar produtos, usúarios, pedidos, estoque e pagamentos."
		)
	
	)
@SecurityScheme(
	name = "bearerAuth",
	type = SecuritySchemeType.HTTP,
	bearerFormat = "JWT",
	scheme = "bearer")


public class OpenApiConfig {
	
}
