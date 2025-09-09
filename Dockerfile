FROM openjdk:21-jdk-slim

WORKDIR /app

# Copie tous les fichiers et dossiers du projet dans le conteneur
COPY . .

# Rend le script Maven exécutable
RUN chmod +x ./mvnw

# Lance la construction du projet avec Maven
RUN ./mvnw clean install -DskipTests

# Expose le port par défaut de l'application
EXPOSE 9000

# Commande pour lancer l'application Spring Boot
CMD ["java", "-jar", "target/api-0.0.1-SNAPSHOT.jar"]