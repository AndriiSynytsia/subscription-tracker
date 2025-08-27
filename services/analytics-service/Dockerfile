FROM eclipse-temurin:21-jre-alpine
COPY target/analytics-service-*.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "/app.jar"]