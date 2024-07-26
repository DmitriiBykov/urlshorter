FROM maven:3.9.8-amazoncorretto-17 AS build
COPY pom.xml /build/
WORKDIR /build/
RUN mvn dependency:go-offline
Copy src /build/src/
RUN mvn package -DskipTests
FROM openjdk:17-jdk-slim
ARG JAR_FILE=/build/target/*.jar
COPY --from=build $JAR_FILE /opt/urlshorter/app.jar
ENTRYPOINT ["java","-jar", "/opt/urlshorter/app.jar"]
EXPOSE 8080
