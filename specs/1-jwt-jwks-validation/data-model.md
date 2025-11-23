# Data Model: JWT/JWKS Validation CLI

## Entities

### JWT Token
- **Fields**: String (compact JWT)
- **Validation**: Must be in standard JWT format (header.payload.signature)

### JWKS URL
- **Fields**: String (URL)
- **Validation**: Must be a valid, reachable HTTPS URL returning JWKS JSON

## Relationships
- JWT is validated against keys fetched from JWKS URL

## Validation Rules
- JWT must be well-formed
- JWKS must be fetched successfully and contain at least one key
- JWT signature must match a key in JWKS
- Expired or tampered JWTs are invalid

## State Transitions
- Input: JWT, JWKS URL
- Fetch JWKS → Validate JWT → Output: valid/invalid/error

## Edge Cases
- Malformed JWT: error
- Unreachable JWKS: error
- No matching key: invalid
- Expired JWT: invalid
