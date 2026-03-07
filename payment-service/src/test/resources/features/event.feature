@event
Feature: Gestion des Event et inscriptions

  Scenario: Création d'un event
    Given un event avec le nom "Microservice", la date "2026-02-28", et une capacité de 100
    When je crée l'event
    Then l'event doit être créé avec succès
