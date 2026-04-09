package com.example.payment_service.Integration;

import com.example.payment_service.DemoApplication;
import com.example.payment_service.model.Event;
import com.example.payment_service.service.EventService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = DemoApplication.class)
@Testcontainers
public class EventServiceIntegrationTest {

    // On prépare le conteneur Docker
    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16")
            .withDatabaseName("paymentdb_test")
            .withUsername("test")
            .withPassword("test");

    // On lie Spring au conteneur Docker dynamiquement
    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
        registry.add("spring.datasource.driver-class-name", () -> "org.postgresql.Driver");
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "create-drop");
    }

    @Autowired
    private EventService eventService;

    @Test
    void testCreateEventInIsolatedDocker() {
        // GIVEN
        Event event = new Event();
        event.setTitre("Test Final sans H2");
        event.setCapaciteMax(50);

        // WHEN
        Event saved = eventService.createEvent(event);

        // THEN
        assertNotNull(saved.getId(), "L'ID devrait être généré par Postgres");
        assertEquals("Test Final sans H2", saved.getTitre());
    }
}