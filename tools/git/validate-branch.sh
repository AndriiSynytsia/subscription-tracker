#!/bin/bash

BRANCH_NAME=$(git branch --show-current)
if [[ ! $BRANCH_NAME =~ ^(feature|fix|chore|docs|hotfix)\/([a-z0-9._-]+\/)*[a-z0-9._-]+(-[0-9]+|-#[0-9]+)$ ]]; then
  echo "❌ Branch name '$BRANCH_NAME' is invalid."
  echo "Use pattern: feature/<area>/<short-desc>-#<issue>"
  exit 1
fi
echo "✅ Branch name OK: $BRANCH_NAME"