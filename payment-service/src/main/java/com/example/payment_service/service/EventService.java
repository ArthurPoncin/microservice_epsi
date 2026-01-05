package com.example.payment_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.payment_service.model.Event;
import com.example.payment_service.model.EventStatus;
import com.example.payment_service.model.Registration;
import com.example.payment_service.repository.EventRepository;
import com.example.payment_service.repository.RegistrationRepository;

@Service
public class EventService {
    private final EventRepository eventRepository;
    private final RegistrationRepository registrationRepository;

    public EventService(final EventRepository eventRepository, RegistrationRepository registrationRepository) {
    this.eventRepository = eventRepository;
    this.registrationRepository = registrationRepository;
}

// --- GESTION EVENTS ---

// Créer un nouvel event
public Event createEvent(Event event){
    if(event.getStatut() == null){
        event.setStatut(EventStatus.BROUILLON);
    }
    return eventRepository.save(event);
}
// Récupérer tous les events
public List<Event> getAllEvents(){
    return eventRepository.findAll();
}
// Récupérer un event par son ID
public Event getEventById(Long id){
    return eventRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Evenement introuvable"));
}
// Mise à jour du statut d'un event
public Event updateStatus(Long id, EventStatus status){
    Event event = getEventById(id);
    event.setStatut(status);
    return eventRepository.save(event);
}

//--- GESTION INSCRIPTIONS ---

public Registration registerUser(Long eventId, String email){
    // Vérifier si l'event existe
    Event event = getEventById(eventId);
    // Vérifier le status de l'event
    if(event.getStatut() != EventStatus.OUVERT){
        throw new RuntimeException("Inscriptions fermées pour cet événement");
    }
    // Vérifier la capacité 
    long currentRegistrations = registrationRepository.countByEventId(eventId);
    if(currentRegistrations >= event.getCapaciteMax()){
        event.setStatut(EventStatus.COMPLET);
        eventRepository.save(event);
        throw new RuntimeException("Event complet");
    }
    // Créer l'inscription
    Registration registration = new Registration(eventId, email);
    return registrationRepository.save(registration);

}
public List<Registration> getUserRegistrations( String email){
    return registrationRepository.findByUtilisateurEmail(email);
    
}


}
