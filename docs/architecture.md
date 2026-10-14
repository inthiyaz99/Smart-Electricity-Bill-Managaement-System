# Architecture

## Components

| Component | Location | Responsibility |
| --- | --- | --- |
| Web application | `frontend` | React UI, client-side routing, and API interaction |
| API service | `backend` | REST endpoints, business rules, persistence, and validation |
| Database | External MySQL instance | Durable application data |

## API structure

The API follows the package flow below:

```text
controller -> repository -> model
```

Future business logic should be placed in a dedicated `service` package, keeping controllers focused on HTTP concerns and repositories focused on data access.

## Configuration

Keep production configuration in the deployment platform's secret manager. The API variables are documented in `backend/.env.example`; configure `DB_*`, `CORS_ALLOWED_ORIGINS`, and `JPA_DDL_AUTO=validate` there. The frontend only accepts public build-time values prefixed with `VITE_`; its deployed API URL is configured in `frontend/.env`.

## Deployment baseline

- Build the web app with `npm run build:frontend`; deploy the generated `frontend/dist` as static assets.
- Build the API with Maven and deploy the Spring Boot artifact or the included Dockerfile. Use `/actuator/health` for the deployment health check.
- Use managed MySQL with backups, TLS, and restricted network access.
- Add CI checks for web lint/build and API tests before deployment.

## Required security work before public launch

The current API has password hashing, but it does not yet enforce authentication or resource ownership. Before a public launch, add token-based authentication, authorize every user-scoped endpoint, add rate limiting to login and registration, and use versioned database migrations instead of automatic schema changes.
