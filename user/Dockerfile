# Use OpenJDK 17 as base image
FROM openjdk:17-jdk-slim

# Expose the application port
EXPOSE 9001

# Add the built JAR file
ADD target/user.jar user.jar

# Set environment variables for Config Server
ENV SPRING_PROFILES_ACTIVE=docker
ENV SPRING_CLOUD_CONFIG_URI=http://config-server:8888
ENV SPRING_CLOUD_CONFIG_NAME=user

# Run the application
ENTRYPOINT ["java", "-jar", "user.jar"]


