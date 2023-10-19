# Use an OpenJDK base image for the build stage
FROM openjdk:17-jdk AS build

# Set the working directory
WORKDIR /workspace/app

# Copy the Maven pom.xml and fetch all dependencies
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
RUN ./mvnw dependency:go-offline

# Copy the application code to the container
COPY src src

# Build the Spring Boot application
RUN ./mvnw package -DskipTests

# Use a slim version of OpenJDK 17 for the run stage
FROM openjdk:17-jdk-slim

# Expose port 8080
EXPOSE 8080

# Copy the built jar from the build stage
COPY --from=build /workspace/app/target/*.jar app.jar

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]