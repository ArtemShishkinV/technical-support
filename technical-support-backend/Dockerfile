#FROM openjdk:17-alpine
#
#EXPOSE 8081
#
#ADD /target/technical-support-backend-1.0-SNAPSHOT.jar technical-support-backend.jar
#
#ENTRYPOINT ["java", "-jar", "technical-support-backend.jar"]

FROM maven:3.8.4-openjdk-17 as builder
WORKDIR /app
COPY . /app/.
RUN mvn -f /app/pom.xml clean package -Dmaven.test.skip=true

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=builder /app/target/*.jar /app/*.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/app/*.jar"]