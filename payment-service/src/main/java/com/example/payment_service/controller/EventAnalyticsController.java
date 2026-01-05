package com.example.payment_service.controller;

import com.example.payment_service.model.EventAnalytics;
import com.example.payment_service.service.EventAnalyticsService;
import org.springframework.web.bind.annotation.*;



import java.util.List;

@CrossOrigin(originPatterns = "http://localhost:*")
@RestController
@RequestMapping("/analytics")
public class EventAnalyticsController {
    
    
    private final EventAnalyticsService service;

    public EventAnalyticsController(EventAnalyticsService service){
        this.service = service;
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
