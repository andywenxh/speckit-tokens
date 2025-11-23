package com.example.tokenvalidator;

public class CLI {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println("Usage: java -jar token-validator.jar <jwt-token> <jwks-url> <x-gtwy-client-id>");
            System.exit(1);
        }
        String jwt = args[0];
        String jwksUrl = args[1];
        String clientId = args[2];
        try {
            JWKSFetcher fetcher = new JWKSFetcher(jwksUrl, clientId);
            TokenValidator validator = new TokenValidator(fetcher.fetchJWKS());
            boolean valid = validator.validate(jwt);
            System.out.println(valid ? "Valid" : "Invalid");
        } 
        catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error: " + e.getMessage());
            System.exit(2);
        }
    }
}
