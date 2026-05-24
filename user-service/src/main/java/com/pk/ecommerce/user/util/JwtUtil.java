package com.pk.ecommerce.user.util;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	private final SecretKey key;
	private final long expirationMs;
	
	public JwtUtil(@Value("${app.jwt.secret}") String secretKey, @Value("${app.jwt.expiration-ms}") long expiry) {
		this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
		this.expirationMs = expiry;
	}
	
	
	public String generateToken(Long userId, String email, String role) {
		
		long now = System.currentTimeMillis();
		
		return Jwts.builder()
		.subject(String.valueOf(userId))
		.claim("email", email)
		.claim("role", role)
		.issuedAt(new Date(now))
		.expiration(new Date(expirationMs))
		.signWith(key)
		.compact();
	}
	
	
	public Jws<Claims> validateToken(String token){
		return Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
	}
}
