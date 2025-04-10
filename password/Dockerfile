# Use OpenJDK 17 as base image
FROM openjdk:17-jdk

# Expose the application port
EXPOSE 9002

# Add the built JAR file
ADD target/password.jar password.jar

# Run the application
ENTRYPOINT ["java", "-jar", "password.jar"]


