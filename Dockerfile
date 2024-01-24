FROM openjdk:17-jdk-alpine as build

WORKDIR /app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

RUN chmod +x ./mvnw
RUN ./mvnw dependency:go-offline -B

COPY src src

RUN ./mvnw clean package

ENTRYPOINT ["java","-jar","/app/target/ServiceEmailTriggerApplication.jar"]
