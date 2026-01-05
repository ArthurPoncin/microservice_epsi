package com.example.payment_service.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;
    private String description;
    private String lieu;
    private LocalDateTime dateHeure;
    private Integer capaciteMax;
    private BigDecimal prix;

    @Enumerated(EnumType.STRING)
    private EventStatus statut;

    // Constructeur vide
    public Event() {}

    // Constructeur complet
    public Event(String titre, String description, String lieu, LocalDateTime dateHeure, Integer capaciteMax, BigDecimal prix, EventStatus statut) {
        this.titre = titre;
        this.description = description;
        this.lieu = lieu;
        this.dateHeure = dateHeure;
        this.capaciteMax = capaciteMax;
        this.prix = prix;
        this.statut = statut;
    }
    //getters
    public Long getId() {
        return id;
    }
    public String getTitre() {
        return titre;
    }
    public String getDescription() {
        return description;
    }
    public String getLieu() {
        return lieu;
    }
    public LocalDateTime getDateHeure() {
        return dateHeure;
    }
    public Integer getCapaciteMax() {
        return capaciteMax;
    }
    public BigDecimal getPrix() {
        return prix;
    }
    public EventStatus getStatut() {
        return statut;
    }

    //setters 

    public void setId(Long id) {
        this.id = id;
    }
    public void setTitre(String titre) {
        this.titre = titre;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public void setDateHeure(LocalDateTime dateHeure) {
        this.dateHeure = dateHeure;
    }
    public void setCapaciteMax(Integer capaciteMax) {
        this.capaciteMax = capaciteMax;
    }
    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }
    public void setStatut(EventStatus statut) {
        this.statut = statut;
    }




    
}
