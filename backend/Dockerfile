# Use OpenJDK as the base image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Build argument to specify the jar file to copy
ARG JAR_FILE=target/*.jar

# Copy the jar file to the container
COPY ${JAR_FILE} achat.jar

# Expose the port your app will run on (default is 8080, adjust if necessary)
EXPOSE 8089

# Run the application using Java
ENTRYPOINT ["java", "-jar", "/app/achat.jar"]

