package com.example.tokenvalidator;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jwt.SignedJWT;
import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jwt.proc.DefaultJWTProcessor;
import com.nimbusds.jose.proc.JWSKeySelector;
import com.nimbusds.jose.proc.JWSVerificationKeySelector;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;

public class TokenValidator {
    private final JWKSet jwkSet;

    public TokenValidator(String jwksJson) throws Exception {
        this.jwkSet = JWKSet.parse(jwksJson);
    }

    public boolean validate(String jwt) throws Exception {
        try {
            SignedJWT signedJWT = SignedJWT.parse(jwt);
            System.out.println("Token Headers: " + signedJWT.getHeader().toJSONObject());
            System.out.println("Token Claims: " + signedJWT.getJWTClaimsSet().toJSONObject());

            DefaultJWTProcessor<SecurityContext> processor = new DefaultJWTProcessor<>();
            JWSAlgorithm alg = signedJWT.getHeader().getAlgorithm();
            JWKSource<SecurityContext> jwkSource = new ImmutableJWKSet<>(jwkSet);
            JWSKeySelector<SecurityContext> keySelector = new JWSVerificationKeySelector<>(alg, jwkSource);
            processor.setJWSKeySelector(keySelector);

            // Find which key is used for verification
            String kid = signedJWT.getHeader().getKeyID();
            if (kid != null) {
                System.out.println("Key used for verification (kid): " + kid);
                if (jwkSet.getKeyByKeyId(kid) != null) {
                    System.out.println("Key details: " + jwkSet.getKeyByKeyId(kid).toJSONObject());
                }
            } else {
                System.out.println("No kid found in token header.");
            }

            processor.process(signedJWT, null);
            return true;
        } catch (JOSEException e) {
            return false;
        }
    }
}
