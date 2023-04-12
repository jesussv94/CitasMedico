package com.practicas.metaEnlace.CitasMedico.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

@Component
public class JWT_Util {
    private static final String pass= "contraseña";

    public String token(String username){
        return JWT.create()
                .withClaim("username", username)
                .sign(Algorithm.HMAC256(pass));
    }
    public String validarToken(String token) throws JWTVerificationException {
        JWTVerifier verificar = JWT.require(Algorithm.HMAC256(pass)).build();
        DecodedJWT decoded = verificar.verify(token);
        return decoded.getClaim("username").asString();
    }
}
