FROM openjdk:17
EXPOSE 8090
ADD target/complaint-0.0.1-SNAPSHOT.jar complaint-dk.jar
ENTRYPOINT ["java", "-jar", "complaint-dk.jar"]