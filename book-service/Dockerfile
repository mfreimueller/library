FROM eclipse-temurin:21-jdk-alpine
VOLUME /tmp
COPY build/libs/book-service-*.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]