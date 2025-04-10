# Use OpenJDK 17 as base image
FROM openjdk:17-jdk

# Expose the application port
EXPOSE 8082

# Add the built JAR file
ADD target/discovery.jar discovery.jar

# Run the application
ENTRYPOINT ["java", "-jar", "discovery.jar"]


