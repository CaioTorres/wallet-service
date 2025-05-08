package com.wallet_service.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wallet_service.domain.Transaction;
import com.wallet_service.domain.Wallet;
import com.wallet_service.domain.enumerated.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public record HistoricalBalanceDTO(
   TransactionType type,
   BigDecimal amount,

   @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
   LocalDateTime timestamp,

   String walletDestinationDocument
) {
    public static HistoricalBalanceDTO toDto(Transaction transaction) {
        return new HistoricalBalanceDTO(
                transaction.getType(),
                transaction.getAmount(),
                transaction.getTimestamp(),
                Optional.ofNullable(transaction.getRelatedWallet())
                        .map(Wallet::getDocument)
                        .orElse(null)
        );
    }
    public static List<HistoricalBalanceDTO> toDto(List<Transaction> transactions){
        return transactions.stream().map(HistoricalBalanceDTO::toDto).toList();
    }
}
