package com.wallet_service.domain.dto;

import jakarta.validation.Valid;

public record DepositDTO(
    @Valid
    Operation operation
) {}
