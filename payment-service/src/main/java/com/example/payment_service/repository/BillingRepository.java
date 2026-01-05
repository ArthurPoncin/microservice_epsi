package com.example.payment_service.repository;

import com.example.payment_service.model.Billing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillingRepository extends JpaRepository<Billing, Long> {

    List<Billing> findByPaymentId(Long paymentId);
}
