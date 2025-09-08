FROM openjdk:21-jdk-slim
WORKDIR /app
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src
RUN ./mvnw clean install -DskipTests
EXPOSE 9000
CMD ["java", "-jar", "target/api-0.0.1-SNAPSHOT.jar"]
