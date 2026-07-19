FROM maven:3.9-eclipse-temurin-21 AS build
LABEL authors="Lenovo"

WORKDIR /app

COPY SpringBootDemo2/pom.xml .
COPY SpringBootDemo2/src src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

ENV PORT=8080

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
