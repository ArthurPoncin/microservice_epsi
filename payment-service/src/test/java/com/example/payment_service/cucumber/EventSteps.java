package com.example.payment_service.cucumber;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.example.payment_service.model.Event;
import com.example.payment_service.service.EventService;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;

@CucumberContextConfiguration
@SpringBootTest
@ActiveProfiles("test") 
@Transactional
public class EventSteps {

    @Autowired
    private EventService eventService;

    private Event event;
    private Event savedEvent;

    @Given("un event avec le nom {string}, la date {string}, et une capacité de {int}")
    public void un_event_avec_le_nom_la_date_et_une_capacité_de(String titre, String date, Integer capacite) {
        event = new Event();
        event.setTitre(titre);
        event.setDateHeure(null);
        event.setCapaciteMax(capacite);
    }

    @When("je crée l'event")
    public void je_crée_l_event() {
        savedEvent = eventService.createEvent(event);
    }

    @Then("l'event doit être créé avec succès")
    public void l_event_doit_être_créé_avec_succès() {
        assertNotNull(savedEvent);
        assertNotNull(savedEvent.getId());
        assertEquals(event.getTitre(), savedEvent.getTitre());
    }
}
