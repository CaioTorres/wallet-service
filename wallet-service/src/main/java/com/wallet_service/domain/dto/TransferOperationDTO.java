package com.wallet_service.domain.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record TransferOperationDTO(
    @Valid
    Operation operation,

    @NotNull(message = "idWallet cant be null")
    UUID destinationWallet
) {
}
