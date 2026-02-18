@echo off
echo 🚀 Starting Subscription Tracker Backend...

docker info >nul 2>&1
if errorlevel 1 (
    echo ❌ Docker is not running. Please start Docker first.
    exit /b 1
)

if not exist .env (
    echo 📝 Creating .env file from template...
    copy .env.example .env
    echo ⚠️  Please update .env with your actual values if needed
)

echo 🐳 Starting backend services...
docker-compose -f infrastructure/docker/docker-compose.dev.yml up -d

echo ⏳ Waiting for services to start...
timeout /t 15 /nobreak >nul

echo 🔍 Checking service health...
curl -f http://localhost:8081/actuator/health >nul 2>&1 && echo ✅ Auth Service: http://localhost:8081 || echo ⚠️  Auth Service starting...
curl -f http://localhost:8086/actuator/health >nul 2>&1 && echo ✅ Subscription Service: http://localhost:8086 || echo ⚠️  Subscription Service starting...

echo.
echo 🎉 Backend is running!
echo 📖 API Docs: http://localhost:8081/swagger-ui.html
echo 🗄️  Database: localhost:5433
echo.
echo To stop: docker-compose -f infrastructure/docker/docker-compose.dev.yml down
