package com.example.payment_service.controller;


import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.payment_service.model.Event;
import com.example.payment_service.model.Registration;
import com.example.payment_service.service.EventService;

@RestController
@RequestMapping("/api/events")
@CrossOrigin(origins = "http://localhost:5173")
public class EventController {

    private final EventService service;

    public EventController(EventService service){
        this.service = service;
    }

    // --- EVENTS ---

    @GetMapping
    public List<Event> getAllEvents(){
        return service.getAllEvents();
    }
    @GetMapping("/{id}")
    public Event getOne(@PathVariable Long id){
        return service.getEventById(id);

    }
    @PostMapping
    public Event create(@RequestBody Event event){
        return service.createEvent(event);
    }

    // --- INSCRIPTIONS ---

    // inscription à un event
    @PostMapping("/{id}/register")
    public Registration registerUser(@PathVariable Long id, @RequestParam String email){
        return service.registerUser(id,email);
    }
    // récupérer les inscriptions d'un utilisateur
    @GetMapping("/my-registrations")
    public List<Registration> getMyRegistrations(@RequestParam String email){
        return service.getUserRegistrations(email);
    }
}
