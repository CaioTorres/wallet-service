package com.wallet_service.controller;

import com.wallet_service.usecase.WalletOperationUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private WalletOperationUseCase useCase;

    @GetMapping("/wallet/{id}")
    public ResponseEntity<?> getTransactionsByWallet(@PathVariable String id){
        return ResponseEntity.ok(useCase.getHistoricalBalance(UUID.fromString(id)));
    }
}
