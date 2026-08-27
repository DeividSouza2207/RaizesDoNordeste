package com.example.raizes_do_nordeste.security;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import com.example.raizes_do_nordeste.domain.entity.Usuario;

@Service
public class JwtService {
	
	private final JwtEncoder jwtEncoder;
	
	public JwtService(JwtEncoder jwtEncoder) {
		this.jwtEncoder =jwtEncoder;
	}
	
	public String gerarToken(Usuario usuario) {
		
		Instant agora = Instant.now();
		
		Instant expiracao = agora.plus(2, ChronoUnit.HOURS);
		
		JwtClaimsSet claims = JwtClaimsSet.builder()
				.subject(usuario.getId().toString())
				.claim("nome", usuario.getNome())
				.claim("role", usuario.getRole().name())
				.issuedAt(agora)
				.expiresAt(expiracao)
				.build();
		
		JwsHeader header = JwsHeader
				.with(MacAlgorithm.HS256)
				.build();
		
		return jwtEncoder.encode(JwtEncoderParameters.from(header, claims)
				)
				.getTokenValue();
	}

}
