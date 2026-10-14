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

Do not commit secrets or environment-specific values. Use environment variables or a local `application-local.properties` file for local-only configuration. Keep production configuration in the deployment platform's secret manager.

## Deployment baseline

- Build the web app with `npm run build:frontend`; deploy the generated `frontend/dist` as static assets.
- Build the API with Maven and deploy the Spring Boot artifact or the included Dockerfile.
- Use managed MySQL with backups, TLS, and restricted network access.
- Add CI checks for web lint/build and API tests before deployment.
