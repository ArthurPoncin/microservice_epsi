package com.example.payment_service.cucumber;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import com.example.payment_service.model.Payment;
import com.example.payment_service.service.PaymentService;
import com.example.payment_service.processor.StripeProcessor;

@CucumberContextConfiguration
@SpringBootTest
public class PaymentSteps {

    @MockBean
    private StripeProcessor stripeProcessor;

    @Autowired
    private PaymentService service;

    private Payment payment;

    @Before
    public void setUp() {
        when(stripeProcessor.getProviderName()).thenReturn("STRIPE");
        when(stripeProcessor.process(any(BigDecimal.class), any(String.class))).thenReturn(true);
    }

    @Given("un paiement pour l'inscription {long} avec un montant de {bigdecimal} et la devise {string}")
    public void createPaymentStep(Long registrationId, BigDecimal montant, String devise) {
        Payment p = new Payment(registrationId, montant, devise, "PENDING", "REF-TEMP");

        p.setPaymentMethod("STRIPE");

        payment = service.createPayment(p);
    }

    @When("je valide le paiement")
    public void validatePaymentStep() {
        payment = service.processPayment(payment.getId());
    }

    @Then("le statut du paiement doit être {string}")
    public void verifyStatusStep(String statutAttendu) {
        assertNotNull(payment);
        assertEquals(statutAttendu, payment.getStatut());
    }
}