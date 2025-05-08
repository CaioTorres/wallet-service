package com.wallet_service.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.wallet_service.domain.enumerated.TransactionType;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    private BigDecimal amount;

    private LocalDateTime timestamp;

    @JsonBackReference
    @ManyToOne
    private Wallet wallet;

    @JsonBackReference
    @ManyToOne
    private Wallet relatedWallet;

    public Transaction(TransactionType type, BigDecimal amount, LocalDateTime timestamp, Wallet wallet, Wallet relatedWallet) {
        this.type = type;
        this.amount = amount;
        this.timestamp = timestamp;
        this.wallet = wallet;
        this.relatedWallet = relatedWallet;
    }

    public Transaction(TransactionType type, BigDecimal amount, LocalDateTime timestamp, Wallet wallet) {
        this.type = type;
        this.amount = amount;
        this.timestamp = timestamp;
        this.wallet = wallet;
    }

    public Transaction() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public Wallet getRelatedWallet() {
        return relatedWallet;
    }

    public void setRelatedWallet(Wallet relatedWallet) {
        this.relatedWallet = relatedWallet;
    }
}
