package com.epsi.demo_cours_microservices.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.epsi.demo_cours_microservices.model.EventAnalytics;
import com.epsi.demo_cours_microservices.repository.EventAnalyticsRepository;

@Service
public class EventAnalyticsService {
    
    private final EventAnalyticsRepository repository;

    public EventAnalyticsService(EventAnalyticsRepository repository) {
        this.repository = repository;
    }

    // Créer un analytics
    public EventAnalytics createAnalytics(Long idEvent){
        EventAnalytics eventAnalytics = new EventAnalytics(idEvent);
        
        return repository.save(eventAnalytics);
    }
    
    // Mise à jour d'un analytics
    public EventAnalytics updateAnalytics(Long idEvent, int newParticipant, double ticketPrice, int capacity){
        EventAnalytics analytics = repository.findByEventId(idEvent);
        
        int participants = analytics.getParticipationCount() + newParticipant;
        analytics.setParticipationCount(participants);
        
        double actualRevenue = analytics.getTotalRevenue();
        analytics.setTotalRevenue(actualRevenue + ticketPrice);
        
        analytics.setOccupancyRate(participants / capacity * 100);
        
        return repository.save(analytics);
    }

    // Récupérer un panier par ID
    public EventAnalytics getEventAnalytics(Long idEvent) {
        return repository.findById(idEvent)
                .orElseThrow(() -> new IllegalArgumentException("Cet événement n'éxiste pas"));
    }

    // Récupère tous les analytics
    public List<EventAnalytics> getAllAnalytics(){
        return repository.findAll();
    }

}

