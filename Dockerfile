# Use an OpenJDK base image for the build stage
FROM openjdk:17-jdk AS build

# Set the working directory
WORKDIR /workspace/app

# Copy the application code to the container
COPY . .

# Build the Spring Boot application
RUN ./gradlew bootJar --no-daemon

# Use a slim version of OpenJDK 19 for the run stage
FROM openjdk:17-jdk-slim

# Expose port 8080
EXPOSE 8080

# Copy the built jar from the build stage
COPY --from=build /workspace/app/build/libs/*.jar app.jar

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
