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