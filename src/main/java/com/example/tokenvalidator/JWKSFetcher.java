package com.example.tokenvalidator;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class JWKSFetcher {
    private final String jwksUrl;
    private final String clientId;

    public JWKSFetcher(String jwksUrl, String clientId) {
        this.jwksUrl = jwksUrl;
        this.clientId = clientId;
    }

    public String fetchJWKS() throws Exception {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(jwksUrl);
            request.setHeader("x-gtwy-client-id", clientId);
            HttpResponse response = client.execute(request);
            int status = response.getStatusLine().getStatusCode();
            if (status != 200) {
                throw new Exception("Failed to fetch JWKS: HTTP " + status);
            }
            return EntityUtils.toString(response.getEntity());
        }
    }
}
