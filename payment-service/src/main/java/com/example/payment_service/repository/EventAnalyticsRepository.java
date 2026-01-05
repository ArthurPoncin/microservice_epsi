package com.epsi.demo_cours_microservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epsi.demo_cours_microservices.model.EventAnalytics;


public interface EventAnalyticsRepository extends JpaRepository<EventAnalytics, Long> {
    
    EventAnalytics findByEventId(Long EventId);
    
} 
