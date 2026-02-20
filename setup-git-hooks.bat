@echo off
echo Setting up local Git hooks...
call npm install
call npm run prepare
call npx husky add .husky/commit-msg "npx --no-install commitlint --edit %%1"
call npx husky add .husky/pre-push "tools/git/validate-branch.bat"
echo ✅ Git hooks configured!
pause
