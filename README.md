# SmartBill Platform

SmartBill is a full-stack utility billing application consisting of a React web client and a Spring Boot API.

## Repository layout

```text
frontend/              React + Vite customer-facing web application
backend/               Spring Boot REST API
docs/                  Architecture and operational documentation
.github/               Repository automation and developer tooling
```

## Prerequisites

- Node.js 20+ and npm 10+
- Java 21+
- MySQL, configured through the API application properties

## Run locally

In separate terminals:

```powershell
npm install
npm run dev:frontend
```

```powershell
cd backend
.\mvnw.cmd spring-boot:run
```

## Quality checks

```powershell
npm run lint:frontend
npm run build:frontend
cd backend
.\mvnw.cmd test
```

See [the architecture guide](docs/architecture.md) for boundaries and deployment considerations.
