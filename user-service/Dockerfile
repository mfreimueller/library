FROM eclipse-temurin:21-jdk-alpine
VOLUME /tmp
COPY build/libs/user-service-*.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app.jar"]