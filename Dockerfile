FROM maven:3.8.3-openjdk-17 AS build
COPY . .
RUN mvn clean install -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/Spring_MVC_CRUD-0.0.1-SNAPSHOT.jar crud.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","crud.jar"]
