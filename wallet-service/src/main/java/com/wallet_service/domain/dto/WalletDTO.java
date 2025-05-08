package com.wallet_service.domain.dto;

import com.wallet_service.domain.Wallet;
import com.wallet_service.domain.enumerated.DocumentType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record WalletDTO(
    UUID id,
    @NotNull(message = "DocumentType cant be null")
    DocumentType documentType,
    @NotNull(message = "Document cant be null")
    @Size(min = 11, max = 14, message = "Document must have between 11 and 14 chars")
    String document,
    @FutureOrPresent(message = "The creation date cant be a past date")
    LocalDateTime createdAt,
    @DecimalMin(value = "0.0", inclusive = true, message = "The balance cant be negative")
    BigDecimal balance
) {
    public static Wallet toEntity(WalletDTO dto){
        return new Wallet(dto.documentType(),
                          dto.document(),
                          dto.createdAt(),
                          dto.balance());
    }

    public static WalletDTO toDto(Wallet wallet){
        return new WalletDTO(
                wallet.getId(),
                wallet.getDocumentType(),
                wallet.getDocument(),
                wallet.getCreatedAt(),
                wallet.getBalance());
    }
}
