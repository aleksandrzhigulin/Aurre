package com.ancapybara.aurre.Security;

import org.apache.tomcat.util.codec.binary.Base64;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class KeysManager {
    public static RSAPrivateKey getRsaPrivateKey(String rsaPrivateKey) {
        try {
            byte[] encoded = Base64.decodeBase64(rsaPrivateKey);

            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encoded);
            return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }

    }

    public static RSAPublicKey getRsaPublicKey(String rsaPublicKey)  {
        try {
            byte[] encoded = Base64.decodeBase64(rsaPublicKey);

            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(encoded);
            return (RSAPublicKey) keyFactory.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }
}
