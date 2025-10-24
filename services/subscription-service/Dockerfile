FROM eclipse-temurin:21-jre-alpine
COPY target/subscription-service-*.jar app.jar
EXPOSE 8086
ENTRYPOINT ["java", "-jar", "/app.jar"]
