package com.wallet_service.domain.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record Operation(
    @NotNull(message = "idWallet cant be null")
    UUID idWallet,

    @NotNull(message = "amount cant be null")
    @DecimalMin(value = "0.0", inclusive = true, message = "The amount cant be negative")
    BigDecimal amount
) {}
