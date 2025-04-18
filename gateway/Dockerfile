# Use OpenJDK 17 as base image
FROM openjdk:17-jdk

# Expose the application port
EXPOSE 8888

# Add the built JAR file
ADD target/gateway.jar gateway.jar

# Run the application
ENTRYPOINT ["java", "-jar", "gateway.jar"]


