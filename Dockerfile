#
# Build stage
#
FROM maven:3.8.3-openjdk-17 AS build

# Set the working directory
WORKDIR /workspace/app

# Copy the application code to the container
COPY . .

# Build the application
RUN mvn clean install

#
# Package stage
#
FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY /workspace/app/target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8080