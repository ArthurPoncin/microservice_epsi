Groupe 3 Arthur Poncin, Théo Renard, Timeo Danois, Aurélien Marchand, Jordan Nkunga

# Partie Client - Microservice Événements

Ce projet est le Frontend (React) de l'application de gestion d'événements. Il permet aux utilisateurs de consulter les événements, de s'inscrire, de simuler un paiement et de gérer leurs réservations.

## Stack Technique

- **Framework :** React (Vite)
- **Langage :** JavaScript
- **Style :** CSS Vanilla
- **Routing :** React Router DOM
- **Http Client :** Fetch API

## Prérequis

Avant de commencer, assurez-vous d'avoir installé :

- [Node.js](https://nodejs.org/) (v16 ou supérieur)
- [npm](https://www.npmjs.com/) (inclus avec Node.js)
- [Docker](https://www.docker.com/) (Optionnel, si lancement via conteneur)

## Installation et Lancement (Méthode Locale)

C'est la méthode recommandée pour le développement (Hot Reload actif).

### 1. Installation des dépendances

Dans le dossier du front :

```bash
cd front
npm install
```

### Configuration de API

Pour configurer l'accès au backend :

1.  Allez dans le dossier `src`.
2.  S'il n'existe pas, créez un dossier `services`.
3.  À l'intérieur, créez un fichier nommé `api.js`.
4.  Collez-y le contenu suivant :

export const API_BASE_URL = "http://localhost:8080/api";

### Arborescence

src/
├── components/ # Composants réutilisables
├── css/ # Feuilles de styles
├── pages/ # Les vues principales
├── services/ # Configuration des appels API
├── App.jsx # Point d'entrée et Routing
└── main.jsx # Montage de l'application

### Partie Microservices

## Microservices Event et Registration

- GET /events : Récupère la liste complète des événements disponibles (Catalogue)
- GET /events/{id} : Récupère les détails complets d'un événement spécifique
- POST /events : Crée un nouvel événement (Organisateur)

- POST /events/{id}/register : Inscrit un utilisateur à un événement (crée un ticket "EN_ATTENTE")
- GET /events/my-registrations : Récupère l'historique des inscriptions d'un utilisateur (via paramètre email)

## Microservices Payments

endpoints :

- POST /payments {"registrationId":123, "montant":50.00," "devise": "EUR", "paymentMethod": "STRIPE", "reference": "REF-XYZ"}
- POST /payments/{id}/process
- GET /payments/{id}

## Microservices Statistiques

endpoints :

- /analytics/{eventid} : Creer les statistiques d'un événement
- /analytics/{eventid}/update : Met à jour les statistique d'un événement
- /analytics/events/{eventId}/stats : Recupère les statistiques d'un évenement grâce a son id
- /analytics/dashboard : Recupère les statistiques de tous les événements
