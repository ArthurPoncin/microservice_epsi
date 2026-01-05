package com.example.payment_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.payment_service.model.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
    // Pour afficher uniquement les événements ouverts au public
    List<Event> findByStatut(String statut); 
}
