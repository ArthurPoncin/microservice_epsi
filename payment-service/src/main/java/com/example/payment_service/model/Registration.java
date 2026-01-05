package com.example.payment_service.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Registration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long eventId; // Lien vers l'événement
    private String utilisateurEmail; // Ou un ID utilisateur si tu as un service User
    private LocalDateTime dateInscription;
    private String statutPaiement; // "EN_ATTENTE", "VALIDE"

    public Registration() {}

    public Registration(final Long eventId, final String utilisateurEmail) {
        this.eventId = eventId;
        this.utilisateurEmail = utilisateurEmail;
        this.dateInscription = LocalDateTime.now();
        this.statutPaiement = "EN_ATTENTE";
    }

    //getters
    public Long getId() {
        return id;
    }
    public Long getEventId() {
        return eventId;
    }
    public String getUtilisateurEmail() {
        return utilisateurEmail;
    }
    public LocalDateTime getDateInscription() {
        return dateInscription;
    }
    public String getStatutPaiement() {
        return statutPaiement;
    }

    //setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public void setUtilisateurEmail(String utilisateurEmail) {
        this.utilisateurEmail = utilisateurEmail;
    }

    public void setDateInscription(LocalDateTime dateInscription) {
        this.dateInscription = dateInscription;
    }

    public void setStatutPaiement(String statutPaiement) {
        this.statutPaiement = statutPaiement;
    }
}


