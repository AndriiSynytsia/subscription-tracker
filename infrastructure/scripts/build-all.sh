#!/bin/bash
mvn clean package -DskipTests
docker-compose -f docker-compose.dev.yml build
