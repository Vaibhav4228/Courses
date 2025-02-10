FROM openjdk:17-jdk-alpine

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} app.jar

EXPOSE 1000

ENTRYPOINT ["java", "-jar", "/app.jar"]
