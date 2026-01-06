package com.example.payment_service.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.payment_service.model.EventAnalytics;
import com.example.payment_service.service.EventAnalyticsService;


@RestController
@RequestMapping("/analytics")
public class EventAnalyticsController {
    
    
    private final EventAnalyticsService service;

    public EventAnalyticsController(EventAnalyticsService service){
        this.service = service;
    }

    @GetMapping("/{eventId}")
    public EventAnalytics createEventStats(@PathVariable Long eventId) {
        return service.createAnalytics(eventId);
    }
    
    @GetMapping("/{eventId}/update")
    public EventAnalytics updateEventStats(@PathVariable Long eventId, int newParticipant, double ticketPrice, int capacity) {
        return service.updateAnalytics(eventId, newParticipant, ticketPrice, capacity);
    }
    
    @GetMapping("/events/{eventId}/stats")
    public EventAnalytics getEventStats(@PathVariable Long eventId) {
        return service.getEventAnalytics(eventId);
    }

    @GetMapping("/dashboard")
    public List<EventAnalytics> getDashboard() {
        return service.getAllAnalytics();
    }

}
