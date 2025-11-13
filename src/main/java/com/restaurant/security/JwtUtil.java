package com.restaurant.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration:86400000}") // default 1 day in ms
    private long expiration;

    private Key key() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateToken(String email, Map<String,Object> claims) {
        return Jwts.builder()
                .setClaims(claims)        // extra details
                .setSubject(email)        // subject = email
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+expiration))
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractEmail(String token){
        return extractAllClaims(token).getSubject();
    }

    public String extractRole(String token) {
        return extractAllClaims(token).get("role", String.class);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
