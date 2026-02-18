#!/bin/bash
set -e

echo "🚀 Starting Subscription Tracker Backend..."

# Check if Docker is running
if ! docker info > /dev/null 2>&1; then
    echo "❌ Docker is not running. Please start Docker first."
    exit 1
fi

# Setup environment if not exists
if [ ! -f infrastructure/docker/.env ]; then
    echo "📝 Creating .env file..."
    cp infrastructure/docker/.env.example infrastructure/docker/.env
fi

# Start services
echo "🐳 Starting services..."
docker-compose -f infrastructure/docker/docker-compose.dev.yml up -d

# Wait for services to be ready
echo "⏳ Waiting for services to start..."
sleep 10

# Health checks
echo "🔍 Checking service health..."
curl -f http://localhost:8081/actuator/health || echo "⚠️  Auth service not ready yet"
curl -f http://localhost:8086/actuator/health || echo "⚠️  Subscription service not ready yet"

echo "✅ Backend is running!"
echo "📖 API Docs: http://localhost:8081/swagger-ui.html"
