package com.example.raizes_do_nordeste.config;

import java.nio.charset.StandardCharsets;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public JwtEncoder jwtEncoder(
			@Value("${jwt.secret}") String secret) {
		
		SecretKey secretKey = new SecretKeySpec(
										secret.getBytes(StandardCharsets.UTF_8),
										"HmacSHA256");
		
		return NimbusJwtEncoder
				.withSecretKey(secretKey)
				.build();
	}
	@Bean
	public JwtDecoder jwtDecoder(
			@Value("${jwt.secret}") String secret) {
		
		SecretKey secretKey = new SecretKeySpec(
				secret.getBytes(StandardCharsets.UTF_8),
				"HmacSHA256");
		
		return NimbusJwtDecoder
				.withSecretKey(secretKey)
				.macAlgorithm(MacAlgorithm.HS256)
				.build();
	}
	@Bean
	public SecurityFilterChain securityFilterChain(
			HttpSecurity http) throws Exception {
		
		http.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests(auth -> auth
					.requestMatchers(
						"/auth/**",
						"/usuarios",
						"/swagger-ui/**",
						"/v3/api-docs/**").permitAll()
					.requestMatchers(HttpMethod.POST, "/pedidos")
						.hasAnyRole("CLIENTE", "ATENDENTE")
					
					.requestMatchers(HttpMethod.PATCH, "/pedidos/*/status")
						.hasAnyRole("COZINHEIRO", "GERENTE", "ADMIN")
						
					.requestMatchers("/admin/")
						.hasRole("ADMIN")
					
					.anyRequest().authenticated())
				
				.oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> {}));
			
			return http.build();
	}
	@Bean
	public JwtAuthenticationConverter jwtAuthenticationConverter() {
		
		JwtGrantedAuthoritiesConverter authorititiesConverter =
				new JwtGrantedAuthoritiesConverter();
		
		authorititiesConverter.setAuthoritiesClaimName("role");
		authorititiesConverter.setAuthorityPrefix("ROLE_");
		
		JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
		
		converter.setJwtGrantedAuthoritiesConverter(authorititiesConverter);
		
		return converter;
		
	}
}
