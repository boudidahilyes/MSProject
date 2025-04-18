# Use OpenJDK 17 as base image
FROM openjdk:17-jdk

# Expose the application port
EXPOSE 9003

# Add the built JAR file
ADD target/tracking-system.jar tracking-system.jar

# Run the application
ENTRYPOINT ["java", "-jar", "tracking-system.jar"]


