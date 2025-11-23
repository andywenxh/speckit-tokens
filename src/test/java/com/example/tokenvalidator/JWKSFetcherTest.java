package com.example.tokenvalidator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JWKSFetcherTest {
    @Test
    public void testJWKSFetcherConstructor() {
        JWKSFetcher fetcher = new JWKSFetcher(
            "https://provider.pst.ontariohealth.ca/api2/public/configuration/.well-known/jwks.json", "27eede387ca725b9906862cdb1c68b9d");
        assertNotNull(fetcher);
    }
}
