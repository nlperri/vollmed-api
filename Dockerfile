FROM maven:3.8.5-openjdk-17

WORKDIR /api
COPY . .

COPY mvnw .
COPY .mvn .mvn

RUN mvn clean install -DskipTests

CMD ["./mvnw", "spring-boot:run"]