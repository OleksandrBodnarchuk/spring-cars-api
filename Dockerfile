# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the application's JAR file to the container
COPY target/*.jar  app.jar

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Set the active profile to 'dev'
ENV SPRING_PROFILES_ACTIVE=dev

# Run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]
