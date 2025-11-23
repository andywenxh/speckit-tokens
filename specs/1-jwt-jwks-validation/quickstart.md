# Quickstart: JWT/JWKS Validation CLI (Java 17, Maven)

## Prerequisites
- Java 17 installed
- Maven installed

## Build
```sh
mvn clean package
```

## Run
```sh
java -jar target/token-validator.jar <jwt-token> <jwks-url>
```

## Example
```sh
java -jar target/token-validator.jar eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9... https://example.com/.well-known/jwks.json
```

## Output
- "Valid" if the token is valid
- "Invalid" if the token is invalid
- Error message if JWT or JWKS is malformed/unreachable

## Test
```sh
mvn test
```
