package com.wallet_service.controller;

import com.wallet_service.domain.dto.DepositDTO;
import com.wallet_service.domain.dto.TransferOperationDTO;
import com.wallet_service.domain.dto.WalletDTO;
import com.wallet_service.domain.dto.WithdrawDTO;
import com.wallet_service.usecase.WalletOperationUseCase;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/wallet")
public class WalletController {

    private static final Logger logger = LoggerFactory.getLogger(WalletController.class);

    @Autowired
    WalletOperationUseCase useCase;

    @PostMapping
    public ResponseEntity<?> createWallet(@Valid @RequestBody WalletDTO dto){
        logger.info("new wallet request for user {}", dto.toString());
        return ResponseEntity.ok(useCase.createWallet(dto));
    }

    @GetMapping("/balance/{id}")
    public ResponseEntity<?> getWalletBalance(@PathVariable String id){
        logger.info("balance request for Wallet {}", id);
        return ResponseEntity.ok(useCase.getWalletBalance(UUID.fromString(id)));
    }

    @PostMapping("/deposit")
    public ResponseEntity<?> deposit(@Valid @RequestBody DepositDTO dto) {
        logger.info("deposit request for wallet {}", dto.toString());
        return ResponseEntity.ok(useCase.deposit(dto));
    }

    @PostMapping("/withdraw")
    public ResponseEntity<?> withdraw(@Valid @RequestBody WithdrawDTO dto) {
        logger.info("withdraw request for wallet {}", dto.toString());
        return ResponseEntity.ok(useCase.withdraw(dto));
    }

    @PostMapping("/transfer")
    public ResponseEntity<?> transfer(@Valid @RequestBody TransferOperationDTO dto){
        logger.info("transfer request for wallet {}", dto.toString());
        return ResponseEntity.ok(useCase.transfer(dto));
    }

}
