package com.example.SocialMedia.Configuration;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;

import java.util.Date;

public class JWTProvider{

    private static SecretKey key=Keys.hmacShaKeyFor(JWTConstant.SECRET_KEY.getBytes());

    public static String generateToken(Authentication auth){
        String jwt= Jwts.builder()
                .setIssuer("Shashwat")
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+86400000))
                .claim("email",auth.getName())
                .signWith(key)
                .compact();
        return jwt;
    }
    public static String getEmailFromJWTToken(String jwt){
        jwt=jwt.substring(7);
        Claims claim=Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
        String email=String.valueOf(claim.get("email"));
        return email;
    }
}
