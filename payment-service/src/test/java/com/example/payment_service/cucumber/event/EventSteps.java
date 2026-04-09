package com.example.payment_service.cucumber.event;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import com.example.payment_service.model.Event;
import com.example.payment_service.repository.EventRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;

@CucumberContextConfiguration
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
    properties = "server.port=8081" 
)
@ActiveProfiles("test")
public class EventSteps {

    @Autowired 
    private EventRepository eventRepository;
    private Long createdEventId;

    private Playwright playwright;
    private Browser browser;
    private Page page;

    private final String FRONT_URL = "http://localhost:5173/create-event"; 

    @Before
    public void setup() {
        playwright = Playwright.create();
        // On garde headless(false),pour voir la page
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(false) 
                .setSlowMo(800)); // Un peu plus lent pour bien voir le remplissage
        page = browser.newPage();
        page.onConsoleMessage(msg -> {
    if ("error".equals(msg.type())) {
        System.out.println("ERREUR NAVIGATEUR : " + msg.text());
    }
});
    }


    @After
    public void tearDown() {
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }

    // --- SCÉNARIO 1 : CRÉATION EVENT ✅ ---

    @Given("un event avec le nom {string}, la date {string} et l'heure {string}, et une capacité de {int}")
    public void remplir_formulaire_vite(String titre, String date, String heure, Integer capacite) {
        page.navigate(FRONT_URL);
        
        String isoDateTime = date + "T" + heure;
        page.fill("input[name='titre']", titre);
        page.fill("textarea[name='description']", "Démo E2E avec Vite et Playwright");
        page.fill("input[name='lieu']", "Salle des Fêtes");
        
        page.fill("input[name='dateHeure']", isoDateTime);
        
        page.fill("input[name='prix']", "0"); 
        page.fill("input[name='capaciteMax']", String.valueOf(capacite));

        page.selectOption("select[name='statut']", "OUVERT");
    }

    @When("je crée l'event")
    public void cliquer_sur_publier() {
        page.click("text=Publier l'événement");
    }

    @Then("l'event doit être créé avec succès")
    public void verifier_redirection_finale() {
        page.waitForURL("**/events", new Page.WaitForURLOptions().setTimeout(5000));
        assertTrue(page.url().contains("/events"));
        
        
        page.waitForTimeout(3000); 
    }

    // --- SCÉNARIO 2 : INSCRIPTION À UN ÉVÉNEMENT ✅ ---

    @Given("un événement existant nommé {string}, la date {string} et l'heure {string}, et une capacité de {int}")
    public void un_evenement_existant(String titre, String date, String heure, Integer capacite) {
        // Implémentation pour créer un événement existant
        Event event = new Event();
        event.setTitre(titre);
        event.setDescription("Événement pour test d'inscription");
        event.setDateHeure(LocalDateTime.parse(date + "T" + heure));
        event.setPrix(BigDecimal.ZERO);
        event.setCapaciteMax(capacite);
        event.setStatut(com.example.payment_service.model.EventStatus.OUVERT);
        
        Event saved = eventRepository.save(event);
        this.createdEventId =saved.getId();

    }

    @Given("je suis sur la page de l'événement")
    public void je_suis_sur_la_page_de_cet_evenement() {
        page.navigate("http://localhost:5173/events/" + createdEventId);
    }

    @When("je m'inscris avec l'email {string}")
    public void je_m_inscris_avec_l_email(String email) {
        page.fill("input[name='email']", email);
        page.click("button:has-text('Réserver'), button:has-text('(Étape 1/2)')");
    }

    @Then("l'inscription doit être réussie")
    public void l_inscription_doit_etre_reussie(){

        page.waitForSelector("text=Place réservée", new Page.WaitForSelectorOptions().setTimeout(5000));
        // Vérification visuelle
        assertTrue(page.isVisible("text=Place réservée"), "l'inscription (étape 1) a échoué !");
        
        System.out.println("Succès : l'inscription est validée, on est prêt pour le paiement.");
        page.waitForTimeout(2000);
    }

}