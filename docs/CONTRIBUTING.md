# 🧭 Contributing to Subscription Tracker

Welcome! Here's how to contribute effectively:

1. Find the [ISSUE](https://github.com/AndriiSynytsia/subscription-tracker/issues) that you would like to work on.
2. Let [me](https://github.com/AndriiSynytsia) know that you would like to take an issue and I will assign it to you.
3. Create a branch from the issue tab that you picked:
   ![How to create branch assigned to issue](docs/images/CreateABranch.png)
4. Rename current branch to the proper format as described in the next section
   ![Rename a branch according to convention](docs/images/RenameABranch.png)

## 🔀 Branch Naming Convention
## IMPORTANT:
**Projects CI will automatically validate your commit messages and branch name. If validation fails, you can fix the last commit or rename the branch.**

### Follow this pattern:
 `<type>/<area>/<description>-#<issue-number>`
- feature/auth/jwt-token-validation-#42
- fix/api/subscription-endpoint-bug-#128
- chore/ci/update-docker-compose-#89
- docs/readme/setup-instructions-#15
- hotfix/security/sql-injection-patch-#200

### Rules
- Type: feature, fix, chore, docs, or hotfix
- Area: Component being changed (e.g., auth, api, ci, frontend)
- Description: Short, lowercase, use hyphens (**no spaces or underscores**)
- Issue: Always reference the GitHub issue number with #

## 🧱 Commit Messages
Follow [Conventional Commits](https://www.conventionalcommits.org/):

### Pattern
    <type>(<scope>): <description>

### Rules
- **Type**: Must be one of the allowed types above
- **Scope**: Optional, use component name (auth, api, frontend, etc.)

- **Description**: Lowercase, no period at the end, imperative mood

- **Length**: Keep first line under 72 characters

### Types
- **feat**: New feature
- **fix**: Bug fix
- **docs**: Documentation changes
- **style**: Code style changes (formatting, semicolons, etc.)
- **refactor**: Code refactoring without changing functionality
- **perf**: Performance improvements
- **test**: Adding or updating tests
- **chore**: Maintenance tasks, dependency updates
- **ci**: CI/CD pipeline changes

### Examples

- feat(auth): add JWT token validation
- fix(api): resolve subscription endpoint 500 error
- docs(readme): update quick start instructions
- chore(deps): bump spring-boot to 3.5.5
- ci(github): add branch name validation workflow
- test(auth): add unit tests for login service
- refactor(subscription): extract validation logic



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
