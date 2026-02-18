# Subscription Tracker

[![CI Auth Service](https://github.com/AndriiSynytsia/subscription-tracker/actions/workflows/ci-auth-service.yml/badge.svg)](https://github.com/AndriiSynytsia/subscription-tracker/actions/workflows/ci-auth-service.yml)
[![CI Subscription Service](https://github.com/AndriiSynytsia/subscription-tracker/actions/workflows/ci-subscription-service.yml/badge.svg)](https://github.com/AndriiSynytsia/subscription-tracker/actions/workflows/ci-subscription-service.yml)

A free, open-source app to track recurring subscriptions and personal expenses.

---
## Quick Start (5 minutes)

### Prerequisites
- Docker & Docker Compose
- Java 21 (for local development)

### Run Backend Only

```bash
# 1. Clone and navigate
git clone <your-repo-url>
cd subscription-tracker

# 2. Setup environment
cp infrastructure/docker/.env.example infrastructure/docker/.env

# 3. Start backend services
docker-compose -f infrastructure/docker/docker-compose.dev.yml up -d

# 4. Verify services are running
curl http://localhost:8081/actuator/health  # Auth Service
curl http://localhost:8086/actuator/health  # Subscription Service
```
Services will be available at:
- Auth Service: http://localhost:8081
- Subscription Service: http://localhost:8086
- Database: localhost:5433
- API Docs: http://localhost:8081/swagger-ui.html
---
### Local Development

#### Start database only
```bash
docker-compose -f infrastructure/docker/docker-compose.dev.yml up -d postgres
```
#### Run services locally
```bash 
./mvnw spring-boot:run -pl services/auth-service
./mvnw spring-boot:run -pl services/subscription-service
```
---
## Features (MVP)

- Track subscriptions: merchant, price, interval, next renewal, payment method
- Get notified before renewals (7 / 3 / 1 days + on the day of renewal)
- Add/edit merchants manually
- Analytics: total amount spend + breakdown per merchant

## Tech Stack

- Backend: Java 21, Spring Boot 3, JPA, Flyway, JWT Auth
- Database: PostgreSQL 16 (via Docker Compose)
- Notifications: Firebase Cloud Messaging
- Docs: OpenAPI 3, Swagger UI

## Workflow

We use a standard SDLC with Milestones → Epics → Issues.  
See [CONTRIBUTING.md](CONTRIBUTING.md) for full guidelines.

### Branching
- `main` is the protected production branch
- Use feature/fix/chore branches as per naming convention
- Use Conventional Commits for commit messages  
