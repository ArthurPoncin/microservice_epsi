package com.example.payment_service.Unitaires;

import com.example.payment_service.model.Event;
import com.example.payment_service.model.EventStatus;
import com.example.payment_service.model.Registration;
import com.example.payment_service.repository.EventRepository;
import com.example.payment_service.repository.RegistrationRepository;
import com.example.payment_service.service.EventService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) 
class EventServiceUnitaryTest {

    @Mock
    private EventRepository eventRepository;

    @Mock
    private RegistrationRepository registrationRepository;

    @InjectMocks
    private EventService eventService; 

    @Test
    void registerUser_ShouldSucceed_WhenEventIsOuvert() {
        // GIVEN
        Long eventId = 1L;
        Event event = new Event();
        event.setStatut(EventStatus.OUVERT);
        event.setCapaciteMax(10);

        // On simule le comportement des repositories
        when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));
        when(registrationRepository.countByEventId(eventId)).thenReturn(5L);
        when(registrationRepository.save(any(Registration.class))).thenAnswer(i -> i.getArguments()[0]);

        // WHEN
        Registration result = eventService.registerUser(eventId, "test@test.com");

        // THEN
        assertNotNull(result);
        assertEquals("test@test.com", result.getUtilisateurEmail());
        verify(registrationRepository, times(1)).save(any()); 
    }

    @Test
    void registerUser_ShouldThrowException_WhenEventIsClosed() {
        // GIVEN
        Long eventId = 1L;
        Event event = new Event();
        event.setStatut(EventStatus.BROUILLON);

        when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));

        // WHEN & THEN
        assertThrows(RuntimeException.class, () -> eventService.registerUser(eventId, "test@test.com"));
        verify(registrationRepository, never()).save(any()); // On vérifie qu'on n'a JAMAIS tenté de sauvegarder
    }
}