package com.ancapybara.aurre.Security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Configuration
@PropertySource("classpath:application.properties")
public class JWTManager {
    @Value("${jwt.private.key}")
    private String RsaPrivateKeyStr;
    @Value("${jwt.public.key}")
    private String RsaPublicKeyStr;

    public JWTManager()  {
    }

    public String createJWTToken(String username) {
        RSAPublicKey rsaPublicKey = KeysManager.getRsaPublicKey(RsaPublicKeyStr);
        RSAPrivateKey rsaPrivateKey = KeysManager.getRsaPrivateKey(RsaPrivateKeyStr);
        Algorithm algorithm = Algorithm.RSA512(rsaPublicKey, rsaPrivateKey);
        String token = JWT
                .create()
                .withClaim("username", username)
                .sign(algorithm);
        return token;
    }

    public boolean verifyJWT(String token, String username) {
        RSAPublicKey rsaPublicKey = KeysManager.getRsaPublicKey(RsaPublicKeyStr);
        RSAPrivateKey rsaPrivateKey = KeysManager.getRsaPrivateKey(RsaPrivateKeyStr);
        Algorithm algorithm = Algorithm.RSA512(rsaPublicKey, rsaPrivateKey);
        JWTVerifier jwtVerifier = JWT.require(algorithm)
                .withClaim("username", username)
                .build();
        DecodedJWT jwt = jwtVerifier.verify(token);
        return jwt.getClaim("username").asString().equals(username);
    }

    public DecodedJWT decodeJWT(String token) {
        return JWT.decode(token);
    }
}
