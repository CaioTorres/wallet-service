package com.wallet_service.domain;

import com.wallet_service.domain.enumerated.DocumentType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/*
 I had problems in my local environment with lombok, so I decided to explicitly create the methods
 */

@Entity
@Data
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private DocumentType documentType;

    private String document;

    private LocalDateTime createdAt;

    private BigDecimal balance;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
    public Wallet(DocumentType documentType, String document, LocalDateTime createdAt, BigDecimal balance) {
        this.documentType = documentType;
        this.document = document;
        this.createdAt = createdAt;
        this.balance = balance;
    }
    public Wallet(){}
}
