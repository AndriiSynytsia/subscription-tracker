# Subscription Tracker

A free, open-source app to track recurring subscriptions and personal expenses.

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

---
## Setup

1. Copy `.env.example` to `.env`
2. Update `.env` with your actual credentials
3. Never commit `.env` to version control

## Workflow

We use a standard SDLC with Milestones → Epics → Issues.  
See [CONTRIBUTING.md](docs/CONTRIBUTING.md) for full guidelines.

### Branching
- `main` is the protected production branch
- Use feature/fix/chore branches as per naming convention
- Use Conventional Commits for commit messages  
