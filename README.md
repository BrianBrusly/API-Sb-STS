# API d'Employés avec Spring Boot et Spring Tool Suite (STS)

Ce projet est une API REST simple développée avec le framework Spring Boot. Il permet de gérer les informations d'employés (création, lecture, mise à jour, suppression) en utilisant une base de données H2 en mémoire pour le développement.

## 📑 Table des matières

- [Technologies et Outils](#️-technologies-et-outils)
- [Fonctionnalités Clés de l'API](#-fonctionnalités-clés-de-lapi)
- [Architecture du Projet](#️-architecture-du-projet)
- [Configuration et Démarrage](#-configuration-et-démarrage)
- [Utilisation de l'API avec Postman](#-utilisation-de-lapi-avec-postman)
- [Utilisation de l'API avec cURL](#-utilisation-de-lapi-avec-curl)
- [Tests et Pratiques de Développement](#-tests-et-pratiques-de-développement)
- [Historique des Versions avec Git](#-historique-des-versions-avec-git)
- [Rétrospective du Projet : Déploiement avec Docker et Render](#-rétrospective-du-projet--déploiement-avec-docker-et-render)

## ⚙️ Technologies et Outils

Ce projet a été construit avec :

- **Java 21** : Langage principal
- **Spring Boot 3.3.4** : Framework pour la création d'applications autonomes
- **Spring Tool Suite (STS)** : IDE basé sur Eclipse pour Spring
- **Maven** : Gestionnaire de dépendances et compilation
- **Spring Web** : Construction d'APIs REST
- **Spring Security** : Sécurisation des endpoints (Basic Auth)
- **H2 Database** : Base de données relationnelle en mémoire
- **Docker** : Conteneurisation de l'application
- **Render** : Plateforme de déploiement cloud
- **Postman** : Test et interaction avec l'API
- **JUnit 5 & Mockito** : Tests unitaires
- **Spring Security Test** : Tests des endpoints sécurisés
- **Git & GitHub** : Versionnement et hébergement

## 🚀 Fonctionnalités Clés de l'API

L'API fournit les opérations CRUD sur les employés :

| Méthode HTTP | Endpoint | Description |
|--------------|----------|-------------|
| GET | `/employees` | Récupère la liste de tous les employés |
| GET | `/employees/{id}` | Récupère un employé spécifique par son ID |
| POST | `/employees` | Crée un nouvel employé |
| PUT | `/employees/{id}` | Met à jour un employé existant |
| DELETE | `/employees/{id}` | Supprime un employé |

## 🏗️ Architecture du Projet

L'application suit une architecture en couches :

### Structure Actuelle (après refactorisation pour le déploiement)

```
projet/
├── app/                              # Dossier contenant l'application Spring Boot
│   ├── src/
│   │   ├── main/java/com/example/
│   │   │   ├── controller/
│   │   │   │   └── EmployeeController.java
│   │   │   ├── service/
│   │   │   │   └── EmployeeService.java
│   │   │   ├── repository/
│   │   │   │   └── EmployeeRepository.java
│   │   │   ├── model/
│   │   │   │   └── Employee.java
│   │   │   └── SecurityConfiguration.java
│   │   └── test/
│   ├── pom.xml
│   ├── mvnw
│   └── mvnw.cmd
├── Dockerfile                        # Configuration Docker à la racine
├── README.md
└── .gitignore
```

### Couches Applicatives

- **controller** : `EmployeeController.java` → Gère les requêtes HTTP
- **service** : `EmployeeService.java` → Contient la logique métier
- **repository** : `EmployeeRepository.java` → Interagit avec la BDD H2
- **model** : `Employee.java` → Représente l'entité de données
- **SecurityConfiguration.java** : Configure la sécurité et l'authentification

## ⚡ Configuration et Démarrage

### 1. Cloner le projet :
```bash
git clone https://github.com/BrianBrusly/API-Sb-STS-.git
```

### 2. Démarrer l'application localement :

> **Note :** `./mvnw` est le Maven Wrapper. C'est un script qui permet d'exécuter des commandes Maven sans avoir besoin d'installer Maven au préalable. Il s'assure que vous utilisez la bonne version du build.

Naviguez dans le dossier de l'application :
```bash
cd API-Sb-STS-/app
```

Lancez l'application :
```bash
./mvnw spring-boot:run
```

📌 **L'application démarre par défaut sur le port 9000.**

### 3. Déploiement avec Docker :

À la racine du projet :
```bash
# Construire l'image Docker
docker build -t employee-api .

# Lancer le conteneur
docker run -p 9000:9000 employee-api
```

## 🧪 Utilisation de l'API avec Postman

L'API est sécurisée avec une Basic Auth (identifiants par défaut : `admin` / `admin`).

### Exemple 1 : Créer un employé
- **URL** : `http://localhost:9000/employees`
- **Méthode** : `POST`
- **Authorization** : Basic Auth (`admin` / `admin`)
- **Body** (raw, JSON) :
```json
{
    "firstName": "Marie",
    "lastName": "Tchouamo",
    "mail": "marie.tchouamo@example.cm",
    "password": "pass_tchouamo"
}
```

### Exemple 2 : Récupérer tous les employés
- **URL** : `http://localhost:9000/employees`
- **Méthode** : `GET`
- **Authorization** : Basic Auth (`admin` / `admin`)

## 🔗 Utilisation de l'API avec cURL

cURL est un outil en ligne de commande pour interagir avec des serveurs.

### Exemple 1 : Créer un employé
```bash
curl --location 'http://localhost:9000/employees' \
--header 'Authorization: Basic YWRtaW46YWRtaW4=' \
--header 'Content-Type: application/json' \
--data '{
    "firstName": "Hassan",
    "lastName": "Oumarou",
    "mail": "hassan.oumarou@example.cm",
    "password": "pass_oumarou"
}'
```

### Exemple 2 : Récupérer tous les employés
```bash
curl --location 'http://localhost:9000/employees' \
--header 'Authorization: Basic YWRtaW46YWRtaW4='
```

## ✅ Tests et Pratiques de Développement

- **Tests Unitaires** : Le projet inclut des tests unitaires pour vérifier le comportement du `EmployeeController`
- **Sécurité** : L'utilisation de `@WithMockUser` (de Spring Security Test) permet de simuler un utilisateur authentifié dans les tests. Cela garantit que la logique de l'API est correcte, même lorsque les endpoints sont sécurisés, sans avoir à gérer manuellement l'authentification dans chaque test

### Outils :
- **Tomcat** : Serveur web embarqué qui gère les requêtes HTTP
- **H2** : Base de données en mémoire utilisée pour un développement rapide
- **Docker** : Conteneurisation pour un déploiement cohérent
- **Postman & cURL** : Clients pour vérifier manuellement les endpoints

## 📜 Historique des Versions avec Git

Le projet est versionné avec Git et hébergé sur GitHub. L'historique des commits retrace les étapes clés du développement :

```bash
git init                    # Initialisation du dépôt local
git remote add origin       # Connexion au dépôt distant
git add .                   # Ajout des fichiers à l'index de Git
git commit -m "..."         # Enregistrement des changements dans le dépôt local
git push -u origin master   # Envoi du code vers GitHub
```

## 🔄 Rétrospective du Projet : Déploiement avec Docker et Render

Cette section retrace les étapes clés du développement et du déploiement de l'API d'Employés, en mettant l'accent sur les défis rencontrés et les solutions apportées. Elle sert de guide pour les futurs déploiements.

### 🏗️ Structure Initiale vs Structure Finale

#### Structure initiale (avant refactorisation)
La structure initiale avait tous les fichiers à la racine du dépôt :
```
projet/
├── src/
├── pom.xml
├── mvnw
├── Dockerfile
└── README.md
```

Cette structure, bien que fonctionnelle pour le développement local, a causé des problèmes lors du déploiement sur Render.

#### Structure finale (après refactorisation)
```
projet/
├── app/                    # Tous les fichiers de l'application Spring Boot
│   ├── src/
│   ├── pom.xml
│   ├── mvnw
│   └── mvnw.cmd
├── Dockerfile              # Configuration Docker à la racine
├── README.md
└── .gitignore
```

### 🌩️ Défis de Déploiement Rencontrés

#### 1. Erreur : `failed to read dockerfile: open Dockerfile: no such file or directory`

**🔴 Problème :** Render ne parvenait pas à trouver le `Dockerfile`.

**🔍 Analyse :**
- Le `Dockerfile` était à la racine du dépôt
- Le `Root Directory` de Render était mal configuré, pointant vers un sous-dossier
- Cela rendait le `Dockerfile` invisible pour le service

**✅ Solution :**
- Configuration Render corrigée : 
  - `Root Directory` : vide ou `.` (valeur par défaut)
  - `Dockerfile Path` : vide ou `./Dockerfile` (valeur par défaut)
- **Leçon :** Importance de la cohérence entre la structure du dépôt et la configuration de l'hébergeur

#### 2. Erreur : `cannot access './mvnw': No such file or directory`

**🔴 Problème :** Docker ne trouvait pas le script `mvnw` pour construire l'application.

**🔍 Analyse :**
- Le `Dockerfile` utilisait `COPY . .` mais le script `mvnw` était dans un sous-dossier
- La commande `RUN ./mvnw clean install` cherchait le script au mauvais endroit

**✅ Solution :**
- Restructuration complète du projet
- Tous les fichiers de l'application déplacés dans le dossier `app/`
- Mise à jour du `Dockerfile` :

```dockerfile
# Ancienne version (problématique)
COPY . .
RUN ./mvnw clean install

# Nouvelle version (fonctionnelle)
COPY app/ .
RUN chmod +x ./mvnw
RUN ./mvnw clean install -DskipTests
```

### ☁️ Déploiement Final Réussi

#### Configuration Docker Finale

```dockerfile
FROM openjdk:21-jdk-slim

WORKDIR /app

# Copier le contenu du dossier app/ vers le conteneur
COPY app/ .

# Rendre le script mvnw exécutable
RUN chmod +x ./mvnw

# Construire l'application
RUN ./mvnw clean install -DskipTests

# Exposer le port
EXPOSE 9000

# Lancer l'application
CMD ["java", "-jar", "target/employee-api-0.0.1-SNAPSHOT.jar"]
```

#### Configuration Render

| Paramètre | Valeur |
|-----------|--------|
| **Root Directory** | *(vide)* ou `.` |
| **Dockerfile Path** | *(vide)* ou `./Dockerfile` |
| **Build Command** | `docker build` |
| **Start Command** | Automatique (défini dans le Dockerfile) |

### ✅ Leçons Apprises

#### 1. **Cohérence de Structure**
- Tous les fichiers de l'application doivent être regroupés logiquement
- Séparer clairement les fichiers de configuration (Dockerfile, README) des fichiers applicatifs

#### 2. **Cohérence des Chemins**
- L'interaction entre les chemins dans le `Dockerfile` et la configuration de l'hébergeur est critique
- Une petite erreur de chemin peut entraîner de longues sessions de débogage

#### 3. **Tests de Déploiement**
- Toujours tester le build Docker en local avant le déploiement
- Vérifier que tous les scripts ont les bonnes permissions (`chmod +x`)

#### 4. **Documentation**
- Documenter chaque problème rencontré et sa solution
- Maintenir une documentation à jour pour les futurs déploiements

### 🚀 Commandes Utiles pour le Déploiement

```bash
# Test du build Docker en local
docker build -t employee-api .
docker run -p 9000:9000 employee-api

# Vérification de la structure après clonage
ls -la                    # Vérifier la présence du Dockerfile à la racine
ls -la app/               # Vérifier la présence des fichiers de l'app
chmod +x app/mvnw         # S'assurer que mvnw est exécutable

# Push des modifications
git add .
git commit -m "Restructuration pour déploiement Render"
git push origin main
```

---

**Développé par BB & Spring Boot**

*Ce projet démontre une approche complète du développement d'API REST, du développement local au déploiement en production, en passant par la containerisation Docker et l'hébergement sur Render.*
