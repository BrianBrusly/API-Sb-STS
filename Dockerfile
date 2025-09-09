# Utiliser une image OpenJDK comme base
FROM openjdk:21-jdk-slim

# Définir le répertoire de travail dans le conteneur
# Ce WORKDIR est la racine de l'application à l'intérieur du conteneur
WORKDIR /app

# Copie tous les fichiers depuis le dossier 'app' de votre dépôt
# vers la racine du répertoire de travail dans le conteneur.
# Le 'app/' à la fin est crucial pour copier le contenu, pas le dossier lui-même.
COPY app/ .

# Rend le script Maven exécutable
RUN chmod +x ./mvnw

# Lance la construction du projet avec Maven
RUN ./mvnw clean install -DskipTests

# Expose le port par défaut de l'application
EXPOSE 9000

# Commande pour lancer l'application Spring Boot
CMD ["java", "-jar", "target/api-0.0.1-SNAPSHOT.jar"]