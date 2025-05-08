FROM alpine/java:21-jdk
WORKDIR /app
COPY target/sistemaVotaciones-0.0.1-SNAPSHOT.jar app_votaciones.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app_votaciones.jar"]