FROM eclipse-temurin:21-jdk-alpine
VOLUME /tmp
COPY build/libs/config-server-*.jar app.jar
EXPOSE 8888
ENTRYPOINT ["java", "-jar", "app.jar"]