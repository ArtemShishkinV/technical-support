FROM openjdk:17-alpine

EXPOSE 8081

ADD /target/technical-support-backend-1.0-SNAPSHOT.jar technical-support-backend.jar

ENTRYPOINT ["java", "-jar", "technical-support-backend.jar"]