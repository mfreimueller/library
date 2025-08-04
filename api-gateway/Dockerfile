FROM eclipse-temurin:21-jdk-alpine
VOLUME /tmp
COPY build/libs/api-gateway-*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]