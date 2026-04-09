@event @e2e
Feature: Gestion des Event et inscriptions
  @event-test
  Scenario: Création d'un event
    Given un event avec le nom "Microservice", la date "2026-02-28" et l'heure "18:00", et une capacité de 100
    When je crée l'event
    Then l'event doit être créé avec succès

  @registration-test
  Scenario: Inscription réussie à un événement
    Given un événement existant nommé "Atelier microservices", la date "2026-05-15" et l'heure "14:00", et une capacité de 50
    And je suis sur la page de l'événement
    When je m'inscris avec l'email "test@example.com"
    Then l'inscription doit être réussie