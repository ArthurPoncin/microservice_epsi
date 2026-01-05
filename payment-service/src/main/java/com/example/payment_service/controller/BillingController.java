package com.example.payment_service.controller;

import com.example.payment_service.model.Billing;
import com.example.payment_service.service.BillingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/billings")
public class BillingController {

    private final BillingService billingService;

    public BillingController(BillingService billingService) {
        this.billingService = billingService;
    }

    @PostMapping("/generate/{paymentId}")
    public ResponseEntity<Billing> generateBilling(@PathVariable Long paymentId) {
        Billing billing = billingService.createBillingFromPayment(paymentId);
        return ResponseEntity.status(HttpStatus.CREATED).body(billing);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Billing> getBilling(@PathVariable Long id) {
        return ResponseEntity.ok(billingService.getBilling(id));
    }

    @GetMapping("/payment/{paymentId}")
    public ResponseEntity<List<Billing>> getBillingsByPayment(@PathVariable Long paymentId) {
        return ResponseEntity.ok(billingService.getBillingsByPaymentId(paymentId));
    }
}
