package com.epsi.demo_cours_microservices.model;

import jakarta.persistence.*;

@Entity
public class EventAnalytics {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long eventId;

    private int participationCount;

    private double totalRevenue;

    private double occupancyRate;

    // Constructeur vide pour JPA
    public EventAnalytics(){}

    // Constructeurs
    public EventAnalytics(Long id, Long eventId, int participationCount, double totalRevenue, double occupancyRate){
        this.id = id;
        this.eventId = eventId;
        this.participationCount = participationCount;
        this.totalRevenue = totalRevenue;
        this.occupancyRate = occupancyRate;
    }

    public EventAnalytics(Long eventId){
        this.eventId = eventId;
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public int getParticipationCount() {
        return participationCount;
    }

    public void setParticipationCount(int participationCount) {
        this.participationCount = participationCount;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public double getOccupancyRate() {
        return occupancyRate;
    }

    public void setOccupancyRate(double occupancyRate) {
        this.occupancyRate = occupancyRate;
    }
    
}
