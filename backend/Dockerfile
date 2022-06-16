#
# Build stage
#
FROM maven:3.8-openjdk-18-slim AS build
COPY src /app/src
COPY pom.xml /app
RUN mvn -f /app/pom.xml clean package -Dmaven.test.skip=true 

#
# Package stage
#
FROM openjdk:18
COPY --from=build /app/target/Backend-0.0.1-SNAPSHOT.jar /app/backend.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/backend.jar"]