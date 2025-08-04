FROM eclipse-temurin:21-jdk-alpine
VOLUME /tmp
COPY build/libs/borrow-service-*.jar app.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "app.jar"]