package com.sansasdev.garagem.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.sansasdev.garagem.entites.Cliente;

public class TokenService {
  @Value("${api.security.token.secret}")
  private String secret;

  public String generateToken(Cliente cliente) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(secret);
      String token = JWT.create().withIssuer("qualquer-nome").withSubject(cliente.getEmail())
          .withExpiresAt(generateExpirationDate())
          .sign(algorithm);
      return token;

    } catch (JWTCreationException exception) {
      throw new RuntimeException("ERRO while generatio toke", exception);
    }
  }

  public String validateToken(String token) {

    try {
      Algorithm algorithm = Algorithm.HMAC256(secret);
      return JWT.require(algorithm).withIssuer("qualquer-nome").build().verify(token).getSubject();

    } catch (JWTCreationException exception) {
      return "";
    }

  }

  private Instant generateExpirationDate() {
    return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
  }

}
