# Research: JWT/JWKS Validation CLI (Java 17, Maven)

## Decision: Java 17 CLI with Maven
- Java 17 is stable, widely supported, and compatible with modern JWT libraries.
- Maven is the standard build tool for Java projects, supports dependency management and testing.

## JWT/JWKS Validation Library
- **Chosen**: Nimbus JOSE + JWT (https://connect2id.com/products/nimbus-jose-jwt)
  - Rationale: Most popular, well-documented, supports JWKS, used in production systems.
  - Alternatives: Auth0 Java JWT (no JWKS support), JJWT (less mature JWKS support)

## HTTP Client
- **Chosen**: Apache HttpClient
  - Rationale: Reliable, easy to use, integrates well with Maven.
  - Alternatives: Java 11+ built-in HttpClient (acceptable, but Apache is more flexible)

## CLI Framework
- **Chosen**: Plain Java (args parsing)
  - Rationale: Simplicity, no need for external CLI frameworks for basic argument parsing.
  - Alternatives: Picocli, JCommander (overkill for this use case)

## Testing
- **Chosen**: JUnit 5
  - Rationale: Standard for Java, integrates with Maven.

## Error Handling
- Print errors to stderr, never log sensitive token data.
- Handle malformed JWT, unreachable JWKS, and validation failures with clear messages.

## Alternatives Considered
- Spring Boot: Overkill for CLI, not needed.
- GUI: Not required (per clarification).
- Other JWT libraries: Lacked JWKS support or less mature.

## References
- Nimbus JOSE + JWT docs: https://connect2id.com/products/nimbus-jose-jwt/examples/jwk-set-fetch
- JWT RFC: https://datatracker.ietf.org/doc/html/rfc7519
- JWKS RFC: https://datatracker.ietf.org/doc/html/rfc7517
