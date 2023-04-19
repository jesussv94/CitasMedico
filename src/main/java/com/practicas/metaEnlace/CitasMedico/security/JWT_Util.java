package com.practicas.metaEnlace.CitasMedico.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class JWT_Util {
    private final static String secret = "$2a$10$bp7LhEAm/T5AhMqpQXFYEetveEgB3rvXrSTk.AMYHvyOULJVqgh.q";
    private final static long tokenValidar = 2_592_000L; //30 días

    public static String generarToken(String username){
        long expira = tokenValidar * 1000;
        Date fechaExpira = new Date(System.currentTimeMillis() + expira);

        Map<String, Object> extra = new HashMap<>();
        extra.put("username", username);

        return Jwts.builder()
                .setSubject(username)
                .setExpiration(fechaExpira)
                .addClaims(extra)
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();
    }
    public static UsernamePasswordAuthenticationToken authenticationToken(String token){
        try{
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(secret.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            String username = claims.getSubject();
            return new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList());
        } catch (JwtException e){
            return null;
        }
    }
}
