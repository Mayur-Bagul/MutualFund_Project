package com.mutualfunds.security;

import com.mutualfunds.model.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    
    private final String SECRET_KEY = "mfams_secret_key_which_should_be_long_enough_to_secure";

    
    private final long EXPIRATION_TIME = 1000L * 60 * 60 * 24 * 8;

    
    private Key getSigningKey() {
        byte[] keyBytes = SECRET_KEY.getBytes(); // You can also use Base64 if encoded
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // 🔐 Generate JWT token
    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("role", user.getRole().name())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // 👤 Extract username (email) from token
    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // ✅ Validate token
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }
}
