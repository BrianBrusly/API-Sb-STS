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

## ⚙️ Technologies et Outils

Ce projet a été construit avec :

- **Java 21** : Langage principal
- **Spring Boot 3.3.4** : Framework pour la création d'applications autonomes
- **Spring Tool Suite (STS)** : IDE basé sur Eclipse pour Spring
- **Maven** : Gestionnaire de dépendances et compilation
- **Spring Web** : Construction d'APIs REST
- **Spring Security** : Sécurisation des endpoints (Basic Auth)
- **H2 Database** : Base de données relationnelle en mémoire
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

### 2. Démarrer l'application :

> **Note :** `./mvnw` est le Maven Wrapper. C'est un script qui permet d'exécuter des commandes Maven sans avoir besoin d'installer Maven au préalable. Il s'assure que vous utilisez la bonne version du build.

Naviguez dans le dossier :
```bash
cd API-Sb-STS-
```

Lancez l'application :
```bash
./mvnw spring-boot:run
```

📌 **L'application démarre par défaut sur le port 9000.**

## 🧪 Utilisation de l'API avec Postman

L'API est sécurisée avec une Basic Auth (identifiants par défaut : `admin` / `admin`).

### Exemple 1 : Créer un employé
- **URL** : `http://localhost:9000/employees`
- **Méthode** : `POST`
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

---

**By BB & Spring Boot**
