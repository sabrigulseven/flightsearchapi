FROM openjdk:17-jdk-slim AS build
LABEL authors="Sabri"

COPY pom.xml mvnw ./
COPY .mvn .mvn
RUN ./mvnw dependency:resolve

COPY src src
RUN ./mvnw package

FROM openjdk:17-jdk-slim
WORKDIR flight
COPY --from=build target/*.jar flight.jar
ENTRYPOINT ["java", "-jar", "flight.jar"]