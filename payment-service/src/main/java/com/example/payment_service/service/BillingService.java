
package com.example.payment_service.service;

import com.example.payment_service.model.Billing;
import com.example.payment_service.model.Payment;
import com.example.payment_service.repository.BillingRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class BillingService {

    private final BillingRepository repository;
    private final PaymentService paymentService;

    public BillingService(BillingRepository repository, PaymentService paymentService) {
        this.repository = repository;
        this.paymentService = paymentService;
    }

    public Billing createBillingFromPayment(Long paymentId) {
        Payment payment = paymentService.getPayment(paymentId);

        if (!"SUCCESS".equals(payment.getStatut())) {
            throw new RuntimeException("Impossible de générer une facture pour un paiement non réussi.");
        }

        Billing billing = new Billing();
        billing.setPaymentId(payment.getId());
        billing.setUserId(payment.getRegistrationId()); // Assuming registrationId maps to userId for now
        billing.setMontant(payment.getMontant());
        billing.setDate(new Date());
        billing.setNumero("BILL-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        billing.setLienPdf("http://billing-service/api/billings/" + billing.getNumero() + "/pdf");

        return repository.save(billing);
    }

    public Billing getBilling(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Facture introuvable"));
    }

    public List<Billing> getBillingsByPaymentId(Long paymentId) {
        return repository.findByPaymentId(paymentId);
    }
}
