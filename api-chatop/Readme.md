# API ChatOp

## Description

API ChatOp est une application RESTful développée avec Spring Boot, permettant de gérer des locations. Elle inclut des fonctionnalités d'authentification, de gestion des utilisateurs, et de téléchargement d'images.

## Fonctionnalités

- **Gestion des utilisateurs** : Inscription, connexion et récupération des détails de l'utilisateur.
- **Gestion des locations** : Création, mise à jour et récupération des informations sur les locations.
- **Téléchargement d'images** : Permet aux utilisateurs de télécharger des images pour leurs annonces de location.
- **Sécurité** : Utilisation de JWT pour sécuriser les endpoints de l'API.

## Prérequis

- Java 21
- Maven
- MySQL (ou un autre SGBD compatible avec JPA)

## Installation

1. Clonez le dépôt :

   ```bash
   git clone https://github.com/votre-utilisateur/api-chatop.git
   ```

2. Accédez au répertoire du projet :

   ```bash
   cd api-chatop
   ```

3. Modifiez le fichier `src/main/resources/application.properties` pour configurer votre base de données.

4. Compilez le projet avec Maven :

   ```bash
   mvn clean install
   ```

5. Exécutez l'application :

   ```bash
   mvn spring-boot:run
   ```

## Utilisation

### Endpoints

- **POST /auth/register** : Inscription d'un nouvel utilisateur.
- **POST /auth/login** : Connexion d'un utilisateur.
- **GET /user/{id}** : Récupérer les détails d'un utilisateur.
- **GET /rentals** : Récupérer toutes les locations.
- **POST /rentals** : Créer une nouvelle location.
- **PUT /rentals/{id}** : Mettre à jour une location existante.


### Documentation Swagger

Après avoir démarré l'application, vous pouvez accéder à la documentation Swagger à l'adresse suivante :

```
http://localhost:3001/swagger-ui/index.html
```

Cela vous permettra de visualiser et d'interagir avec les endpoints de l'API de manière conviviale.
