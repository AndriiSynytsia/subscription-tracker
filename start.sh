#!/bin/bash
set -e

echo "🚀 Starting Subscription Tracker Backend..."

# Check if Docker is running
if ! docker info > /dev/null 2>&1; then
    echo "❌ Docker is not running. Please start Docker first."
    exit 1
fi

# Setup environment if not exists
if [ ! -f .env ]; then
    echo "📝 Creating .env file from template..."
    cp .env.example .env
    echo "⚠️  Please update .env with your actual values if needed"
fi

# Start services
echo "🐳 Starting backend services..."
docker-compose -f infrastructure/docker/docker-compose.dev.yml up -d

# Wait for services to be ready
echo "⏳ Waiting for services to start..."
sleep 15

# Health checks
echo "🔍 Checking service health..."
if curl -f http://localhost:8081/actuator/health > /dev/null 2>&1; then
    echo "✅ Auth Service: http://localhost:8081"
else
    echo "⚠️  Auth Service starting..."
fi

if curl -f http://localhost:8086/actuator/health > /dev/null 2>&1; then
    echo "✅ Subscription Service: http://localhost:8086"
else
    echo "⚠️  Subscription Service starting..."
fi

echo ""
echo "🎉 Backend is running!"
echo "📖 API Docs: http://localhost:8081/swagger-ui.html"
echo "🗄️  Database: localhost:5433"
echo ""
echo "To stop: docker-compose -f infrastructure/docker/docker-compose.dev.yml down"
