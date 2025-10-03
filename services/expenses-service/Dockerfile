FROM eclipse-temurin:21-jre-alpine

# Copy JAR file
COPY target/expense-service-*.jar app.jar

# Expose port
EXPOSE 8084

# Run application
ENTRYPOINT ["java", "-jar", "/app.jar"]