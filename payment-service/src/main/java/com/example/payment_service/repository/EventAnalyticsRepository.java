package com.example.payment_service.repository;

import com.example.payment_service.model.EventAnalytics;
import org.springframework.data.jpa.repository.JpaRepository;



public interface EventAnalyticsRepository extends JpaRepository<EventAnalytics, Long> {
    
    EventAnalytics findByEventId(Long EventId);
    
} 
