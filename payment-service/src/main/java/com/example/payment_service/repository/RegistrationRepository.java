package com.example.MicroService_Event.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.MicroService_Event.model.Registration;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    List<Registration> findByEventId(Long eventId);
    List<Registration> findByUtilisateurEmail(String email);
    // Compter combien de gens sont inscrits à un event
    long countByEventId(Long eventId);
}