package com.wallet_service.repository;

import com.wallet_service.domain.Transaction;
import com.wallet_service.domain.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    List<Transaction> findByWalletOrderByTimestampDesc(Wallet wallet);
}
