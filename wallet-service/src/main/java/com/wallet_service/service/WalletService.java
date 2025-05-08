package com.wallet_service.service;

import com.wallet_service.domain.Wallet;

import java.math.BigDecimal;
import java.util.UUID;

public interface WalletService {
    Wallet createWallet(Wallet wallet);
    BigDecimal getWalletBalance(UUID id);
    Wallet deposit(UUID id, BigDecimal amount);
    Wallet withdraw(UUID id, BigDecimal amount);
    Wallet getWallet(UUID id);
}
