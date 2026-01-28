package com.example.AutoHub.security;

import com.example.AutoHub.user.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtils {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration.ms}")
    private long jwtExpirationMs;

    public String generateToken(UserDetails userDetails){
       return Jwts.builder()
               .setSubject(userDetails.getUsername())
               .claim("roles",userDetails.getAuthorities().stream()
                       .map(GrantedAuthority::getAuthority).toList()
               )
               .setIssuedAt(new Date())
               .setExpiration(new Date(System.currentTimeMillis()+jwtExpirationMs))
               .signWith(SignatureAlgorithm.HS256,jwtSecret)
               .compact();
    }

    public Claims getClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(jwtSecret)
                .build()
                .parseClaimsJws(token).getBody();
    }
}
