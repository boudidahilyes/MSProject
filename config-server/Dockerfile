# Use OpenJDK 17 as base image
FROM openjdk:17-jdk

# Expose the application port
EXPOSE 8081

# Add the built JAR file
ADD target/config-server.jar config-server.jar

# Run the application
ENTRYPOINT ["java", "-jar", "config-server.jar"]


