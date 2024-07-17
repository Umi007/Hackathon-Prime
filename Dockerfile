# Stage 1: Build stage with Maven
FROM maven:3.8.4-openjdk-17 AS build

# Set working directory
WORKDIR /app

# Copy Maven dependencies and build application
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy source code and build application
COPY src src
RUN mvn clean package -DskipTests

# Stage 2: Production-ready stage with Eclipse Temurin OpenJDK
FROM eclipse-temurin:17-jdk

# Create a non-root user
RUN groupadd -r spring && useradd --no-log-init -r -g spring spring

# Set working directory
WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Change ownership of the application JAR
RUN chown spring:spring app.jar

# Switch to the non-root user
USER spring

# Expose port 8080 (if your application uses this port)
EXPOSE 8080

# Define the command to run your application
ENTRYPOINT ["java", "-jar", "app.jar"]
