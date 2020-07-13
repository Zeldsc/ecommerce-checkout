# - Build
FROM maven:3.6.0-jdk-11-slim AS MAVEN_BUILD

COPY pom.xml /build/
COPY src /build/src/
WORKDIR /build/
RUN mvn clean package 

MAINTAINER FELIPE-CALDAS

# - For Java 8, try this
FROM openjdk:11-jre-slim

# - Refer to Maven build
ARG JAR_FILE=/build/target/ecommerce-checkout.war

# - cd /opt/app
WORKDIR /app

# - cp war file
COPY --from=MAVEN_BUILD ${JAR_FILE} app.war

# java -jar /opt/app/app.jar
CMD ["java", "-jar", "-Dspring.profiles.active=default", "app.war"]

EXPOSE 8080/tcp
