FROM eclipse-temurin:21-jdk-alpine
VOLUME /tmp
COPY build/libs/eureka-server-*.jar app.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "/app.jar"]