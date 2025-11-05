# 🧭 Contributing to Subscription Tracker

Welcome! Here's how to contribute effectively:

## 🔀 Branch Naming Convention

### Follow this pattern:
- feature/<scope>-<short-description>
- fix/<scope>-<short-description>
- docs/<scope>-<short-description>
- chore/<scope>-<short-description>

✅ Examples:
- feature/frontend-login-ui
- fix/auth-jwt-validation
- chore/devops-husky-setup

## 🧱 Commit Messages
Follow [Conventional Commits](https://www.conventionalcommits.org/):
- feat(auth): add JWT validation
- fix(api): correct subscription endpoint
- docs(readme): update setup instructions


## 🧪 Pull Requests
Each PR must:
- Reference a related issue (e.g., `Closes #42`)
- Pass all checks (lint, build, test)
- Have clear description and checklist completed

## 🧠 Code Review
- Minimum 1 approval before merge
- No direct commits to `main` — use PRs
- Rebase before merging

## Project Workflow & SDLC

### Milestones
- Foundations (monorepo, CI, SDK, basic UI)
- MVP (auth, subscriptions CRUD, profile)
- Hardening (analytics, error handling, e2e tests, perf)
- Beta/Release (store submission, prod infra)

### Issue Lifecycle

#### Definition of Ready (DoR)
An issue is *Ready* when:
- ✅ Business goal clearly stated
- ✅ Acceptance Criteria & Definition of Done listed
- ✅ Designs or wireframes attached or referenced
- ✅ Dependencies identified
- ✅ Estimate assigned (`size:S`, `size:M`, `size:L`)
- ✅ Label `status:ready` applied

#### Definition of Done (DoD)
An issue is *Done* when:
- ✅ Code implemented, linted & tested
- ✅ Pull Request approved by code owner
- ✅ Documentation updated (README, CHANGELOG, docs/)
- ✅ Feature toggled (if applicable)
- ✅ Merge into `main` + release tag (if required)