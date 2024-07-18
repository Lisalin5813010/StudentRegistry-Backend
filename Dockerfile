# Use the openjdk:17-jdk-slim base image
FROM openjdk:17-jdk-slim

# Add a volume pointing to /tmp
VOLUME /tmp
# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file of your application into the container
COPY target/StudentRegistryBackend-0.0.1-SNAPSHOT.jar /app/StudentRegistryBackend-0.0.1-SNAPSHOT.jar

# Expose the port your application runs on
EXPOSE 8081

# Command to run the application
CMD ["java", "-jar", "/app/StudentRegistryBackend-0.0.1-SNAPSHOT.jar"]


