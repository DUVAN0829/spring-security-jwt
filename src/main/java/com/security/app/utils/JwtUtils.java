package com.security.app.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class JwtUtils {

    //Vars
    @Value("${security.secret.key}")
    private String secretKey;

    @Value("${security.jwt.user.generator}")
    private String userGenerator;

    //Method: Create token
    public String createToken(Authentication authentication) {

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        String username = authentication.getPrincipal().toString();

        String authorities = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(",")); //* Retorna los authorities separados por coma: READ, WRITE, ETC.

        return JWT.create()
                .withIssuer(userGenerator)
                .withSubject(username)
                .withClaim("authorities", authorities)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 1800000)) //* Tiempo de expiración del token, en este caso 30 minutos en milisegundos.
                .withJWTId(UUID.randomUUID().toString())
                .withNotBefore(new Date(System.currentTimeMillis())) //* Desde que momento el token es valido, en este caso, desde este momento.
                .sign(algorithm);

    }

    //Method: Validate token

}
