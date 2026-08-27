package com.example.raizes_do_nordeste;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RaizesDoNordesteApplication {

	public static void main(String[] args) {
		SpringApplication.run(RaizesDoNordesteApplication.class, args);
	}

	@Bean
	CommandLineRunner verificarJwtSecret() {
	    return args -> {
	        String secret = System.getenv("JWT_SECRET_KEY");

	        if (secret == null || secret.isBlank()) {
	            System.out.println("❌ JWT_SECRET_KEY NÃO encontrada.");
	        } else {
	            System.out.println("✅ JWT_SECRET_KEY encontrada.");
	            System.out.println("Tamanho: " + secret.length() + " caracteres.");
	        }
	    };
	}
}
