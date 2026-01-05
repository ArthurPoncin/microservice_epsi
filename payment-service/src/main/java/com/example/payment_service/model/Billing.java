package com.example.payment_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Billing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long paymentId;

    private BigDecimal montant;

    private Date date;

    private String numero;

    private String lienPdf;

    public Billing(){

    }

    public Billing(Long id, Long userId, Long paymentId, BigDecimal montant, Date date, String numero, String lienPdf) {
        this.id = id;
        this.userId = userId;
        this.paymentId = paymentId;
        this.montant = montant;
        this.date = date;
        this.numero = numero;
        this.lienPdf = lienPdf;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getLienPdf() {
        return lienPdf;
    }

    public void setLienPdf(String lienPdf) {
        this.lienPdf = lienPdf;
    }
}