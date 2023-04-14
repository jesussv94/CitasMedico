package com.practicas.metaEnlace.CitasMedico.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
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
        /*
        return JWT.create()
                .withSubject("user")
                .withClaim("username", username)
                .withIssuedAt(new Date())
                .sign(Algorithm.HMAC256(secret));
        */
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
    /*
    public String validarToken(String token) throws JWTVerificationException {
        JWTVerifier validar = JWT.require(Algorithm.HMAC256(secret))
                .withSubject("user")
                .build();
        DecodedJWT decode = validar.verify(token);
        return decode.getClaim("username").asString();
      }*/

}
