package com.wallet_service.service;

import com.wallet_service.domain.Transaction;
import com.wallet_service.domain.Wallet;
import com.wallet_service.domain.dto.Operation;
import com.wallet_service.domain.enumerated.TransactionType;

import java.util.List;

public interface TransactionService {
    List<Transaction> getTransactionsByWallet(Wallet wallet);
    Transaction createTransaction(Wallet wallet, Wallet relatedWallet, Operation operation, TransactionType type);
}
