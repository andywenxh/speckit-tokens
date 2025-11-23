# JWT/JWKS Validation CLI

A Java 17 CLI application to validate JWT tokens against a JWKS URL, using a custom `x-gtwy-client-id` HTTP header.

## Usage

```
java -jar target/token-validator.jar <jwt-token> <jwks-url> <x-gtwy-client-id>
```

- Prints "Valid" if the token is valid
- Prints "Invalid" if the token is invalid
- Prints error messages for malformed JWT or unreachable JWKS

## Build

```
mvn clean package
```

## Test

```
mvn test
```

See `specs/1-jwt-jwks-validation/quickstart.md` for more details.
