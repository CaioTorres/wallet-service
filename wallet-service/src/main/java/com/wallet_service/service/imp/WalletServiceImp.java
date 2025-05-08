package com.wallet_service.service.imp;

import com.wallet_service.domain.Wallet;
import com.wallet_service.exception.InsuficientBalanceException;
import com.wallet_service.exception.WalletNotFoundException;
import com.wallet_service.repository.WalletRepository;
import com.wallet_service.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class WalletServiceImp implements WalletService {

    @Autowired
    private WalletRepository repository;

    @Override
    public Wallet createWallet(Wallet wallet) {
        wallet.setCreatedAt(LocalDateTime.now());
        return repository.save(wallet);
    }

    @Override
    public BigDecimal getWalletBalance(UUID id) {
        return getWallet(id).getBalance();
    }

    @Override
    public Wallet deposit(UUID id, BigDecimal amount) {
        var wallet = getWallet(id);
        var updatedBalance =  wallet.getBalance().add(amount);
        wallet.setBalance(updatedBalance);
        return repository.save(wallet);
    }

    @Override
    public Wallet withdraw(UUID id, BigDecimal amount) {
        var wallet = getWallet(id);

        if (wallet.getBalance().compareTo(amount) < 0) {
            throw new InsuficientBalanceException("insufficient balance to make the withdrawal", "INSUFICIENT_BALANCE");
        }

        var updatedBalance = wallet.getBalance().subtract(amount);
        wallet.setBalance(updatedBalance);
        return repository.save(wallet);
    }

    public Wallet getWallet(UUID id) {
        return repository.findById(id)
            .orElseThrow( ()-> new WalletNotFoundException("Wallet not found", "WALLET_NOT_FOUND"));
    }
}
