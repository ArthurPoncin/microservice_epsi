package com.example.payment_service.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.payment_service.model.Payment;
import com.example.payment_service.model.Registration;
import com.example.payment_service.processor.PaymentProcessor;
import com.example.payment_service.repository.PaymentRepository;
import com.example.payment_service.repository.RegistrationRepository;


@Service
public class PaymentService {

    private final PaymentRepository repository;
    // Stripe ou Paypal
    private final Map<String, PaymentProcessor> processors;

    public PaymentService(PaymentRepository repository, List<PaymentProcessor> processorList) {
        this.repository = repository;

        this.processors = processorList.stream()
                .collect(Collectors.toMap(PaymentProcessor::getProviderName, Function.identity()));
    }
    @Autowired
    private RegistrationRepository registrationRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    public Payment createPayment(Payment payment) {
        // Obligé de valider (on simule un succès Stripe)
        payment.setStatut("VALIDE");
        
        // On sauvegarde du paiement 
        Payment savedPayment = paymentRepository.save(payment);
        
        // Recherche de l'inscription liée à ce paiement
        Registration registration = registrationRepository.findById(payment.getRegistrationId())
                .orElseThrow(() -> new RuntimeException("Inscription introuvable pour l'ID : " + payment.getRegistrationId()));

        // Mise à jour du statut de l'inscription pour le Frontend
        registration.setStatutPaiement("VALIDE");

        // Sauvegarde
        registrationRepository.save(registration);

        return savedPayment;
    }

    public Payment processPayment(Long id) {
        Payment payment = getPayment(id);

        if (!"PENDING".equals(payment.getStatut())) {
            throw new RuntimeException("Paiement déjà traité ou en erreur.");
        }

        String method = payment.getPaymentMethod();

        if (method == null || !processors.containsKey(method)) {
            throw new RuntimeException("Méthode de paiement inconnue : " + method);
        }

        PaymentProcessor selectedProcessor = processors.get(method);

        System.out.println(">>> Délégation du paiement au processeur : " + method);
        boolean success = selectedProcessor.process(payment.getMontant(), payment.getReference());

        if (success) {
            payment.setStatut("SUCCESS");
        } else {
            payment.setStatut("FAILED");
        }

        return repository.save(payment);
    }

    public Payment getPayment(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Paiement introuvable"));
    }

    public List<Payment> getPaymentsByRegistration(Long registrationId) {
        return repository.findByRegistrationId(registrationId);
    }

    public List<Payment> getAllPayments() {
        return repository.findAll();
    }

    public BigDecimal calculerMontantTotal(BigDecimal montantHT, BigDecimal tauxTVA) {
        BigDecimal montantTaxe = montantHT.multiply(tauxTVA);
        return montantHT.add(montantTaxe);
    }
}