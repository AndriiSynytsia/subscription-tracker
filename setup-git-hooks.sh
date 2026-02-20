#!/bin/bash
echo "Setting up local Git hooks..."
npm install
npm run prepare
npx husky add .husky/commit-msg 'npx --no-install commitlint --edit $1'
npx husky add .husky/pre-push 'tools/git/validate-branch.sh'
echo "✅ Git hooks configured!"
