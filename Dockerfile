FROM openjdk:21-jdk-slim

WORKDIR /app

COPY .mvn/ .mvn/
COPY pom.xml .
COPY src/main/ src/main/
COPY src/test/ src/test/

RUN ./mvnw clean install -DskipTests

EXPOSE 9000

CMD ["java", "-jar", "target/api-0.0.1-SNAPSHOT.jar"]