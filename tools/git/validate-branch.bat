@echo off
for /f "tokens=*" %%i in ('git branch --show-current') do set BRANCH_NAME=%%i
echo Checking branch: %BRANCH_NAME%

echo %BRANCH_NAME% | findstr /r /c:"^feature/" >nul && goto :valid
echo %BRANCH_NAME% | findstr /r /c:"^fix/" >nul && goto :valid
echo %BRANCH_NAME% | findstr /r /c:"^chore/" >nul && goto :valid
echo %BRANCH_NAME% | findstr /r /c:"^docs/" >nul && goto :valid
echo %BRANCH_NAME% | findstr /r /c:"^hotfix/" >nul && goto :valid

echo ❌ Branch name '%BRANCH_NAME%' is invalid.
echo Use pattern: feature/area/short-desc-#issue
exit /b 1

:valid
echo ✅ Branch name OK: %BRANCH_NAME%
