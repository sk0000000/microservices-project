# Microservices Project

Ce projet contient plusieurs microservices qui interagissent entre eux via Spring Cloud. Voici une brève description de chaque microservice inclus dans ce projet :

## Microservices

### 1. `config-server`
- **Description** : Le serveur de configuration central qui utilise Spring Cloud Config pour centraliser et fournir la configuration pour les autres microservices.
- **Port** : `8888`
- **Technologies** : Spring Boot, Spring Cloud Config Server, H2 Database

### 2. `eureka-server`
- **Description** : Le serveur Eureka qui agit comme un registre de services pour permettre aux microservices de se découvrir et de se communiquer.
- **Port** : `9001`
- **Technologies** : Spring Boot, Spring Cloud Eureka

### 3. `zuul-server`
- **Description** : Le serveur de passerelle (API Gateway) qui gère les requêtes des clients et les redirige vers les microservices appropriés.
- **Port** : `9004`
- **Technologies** : Spring Boot, Spring Cloud Zuul, Spring Cloud Eureka

### 4. `m-commandes`
- **Description** : Le microservice pour gérer les commandes, incluant les opérations CRUD pour les commandes.
- **Port** : `9000`
- **Technologies** : Spring Boot, Spring Data JPA, H2 Database, Spring Cloud Eureka

### 5. `m-produits`
- **Description** : Le microservice pour gérer les produits.
- **Port** : `9002`
- **Technologies** : Spring Boot, Spring Data JPA, H2 Database, Spring Cloud Eureka

## Prérequis

- Java 11 ou version ultérieure
- Maven 3.6 ou version ultérieure
- H2 Database pour config-server (optionnel pour test)
- Accès à GitHub pour récupérer les fichiers de configuration

## Instructions de démarrage

1. Clonez ce repository sur votre machine locale :
   ```bash
   git clone https://github.com/votre-nom-utilisateur/microservices-project.git
   ```

2. Allez dans chaque répertoire des microservices (`zuul-server`, `m-commandes`, `m-produits`, `eureka-server`, `config-server`) et exécutez la commande suivante pour démarrer les services :

   ```bash
   mvn spring-boot:run
   ```

3. Assurez-vous que chaque service fonctionne correctement en accédant à leurs ports respectifs.

## Configuration du serveur de configuration (config-server)

- Le serveur de configuration récupère les configurations depuis un repository Git distant pour être appliqué à tous les microservices.
- Vous pouvez configurer votre repository Git distant dans `application.properties` du `config-server`.

## Contributions

Vous pouvez proposer des améliorations en ouvrant des issues ou en soumettant des pull requests.

## License

Ce projet est sous licence MIT. Voir le fichier [LICENSE](LICENSE) pour plus de détails.
