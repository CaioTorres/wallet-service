package com.wallet_service.service.imp;

import com.wallet_service.domain.Transaction;
import com.wallet_service.domain.Wallet;
import com.wallet_service.domain.dto.Operation;
import com.wallet_service.domain.enumerated.TransactionType;
import com.wallet_service.repository.TransactionRepository;
import com.wallet_service.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionServiceImp implements TransactionService {

    @Autowired
    private TransactionRepository repository;

    @Override
    public List<Transaction> getTransactionsByWallet(Wallet wallet) {
        return repository.findByWalletOrderByTimestampDesc(wallet);
    }

    @Override
    public Transaction createTransaction(Wallet wallet, Wallet relatedWallet, Operation operation, TransactionType type) {
        Transaction transaction = new Transaction(type,
                operation.amount(),
                LocalDateTime.now(),
                wallet,
                relatedWallet);
        return repository.save(transaction);
    }
}
