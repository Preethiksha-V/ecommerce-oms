# Stage 1: Build the JAR inside a container
FROM maven:3.8.5-openjdk-17 AS build
COPY . .
# We skip tests to move faster and avoid plugin issues
RUN mvn clean package -DskipTests

# Stage 2: Create the final image
FROM eclipse-temurin:17-jdk-alpine
COPY --from=build /target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
