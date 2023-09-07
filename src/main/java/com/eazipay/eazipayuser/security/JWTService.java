package com.eazipay.eazipayuser.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
@Slf4j
@Component
public class JWTService {
    @Value("${jwt.secretToken}")
    private String secretToken;

    private Key getSigningKey() {
        byte[] keyBytes = secretToken.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(String email) {
        Date currentDate = new Date(System.currentTimeMillis());
        Date expiryDate = new Date(System.currentTimeMillis() + 60000 * 24); // 24 hours expiration

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(currentDate)
                .setExpiration(expiryDate)
                .signWith(getSigningKey(), SignatureAlgorithm.HS512)
                .compact();
    }



}
