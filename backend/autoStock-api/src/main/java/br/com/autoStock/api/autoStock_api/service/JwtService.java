package br.com.autoStock.api.autoStock_api.service;

import java.util.Date;
import org.springframework.stereotype.Service;
import br.com.autoStock.api.autoStock_api.model.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Claims;
import java.security.Key;


@Service
public class JwtService {

    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    private final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hora

    public String generateToken(Usuario user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("role", user.getRole().name())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractEmail(String token) {
        return getClaims(token).getSubject();
    }

    public boolean isTokenValid(String token, Usuario user) {
        final String email = extractEmail(token);
        return (email.equals(user.getEmail()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}

// "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkZWRvQGVtYWlsLmNvbSIsInJvbGUiOiJBRE1JTiIsImlhdCI6MTc3Njc4MDczNCwiZXhwIjoxNzc2Nzg0MzM0fQ.ld58TOKcLtzXqw5sARTdMgBoZANXFAK2Et7SZD9hkyQ"