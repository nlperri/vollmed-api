FROM maven:3.8.3-openjdk-17 AS build

COPY src /home/vollmed-api/src
COPY pom.xml /home/vollmed-api
RUN mvn -f /home/vollmed-api/pom.xml clean package -DskipTests
EXPOSE 8080
ENTRYPOINT ["java","-jar","/home/vollmed-api/target/api-0.0.1-SNAPSHOT.jar"]