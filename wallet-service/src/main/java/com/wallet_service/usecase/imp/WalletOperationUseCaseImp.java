package com.wallet_service.usecase.imp;

import com.wallet_service.controller.WalletController;
import com.wallet_service.domain.Transaction;
import com.wallet_service.domain.dto.*;
import com.wallet_service.domain.enumerated.TransactionType;
import com.wallet_service.service.TransactionService;
import com.wallet_service.service.WalletService;
import com.wallet_service.usecase.WalletOperationUseCase;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class WalletOperationUseCaseImp implements WalletOperationUseCase {

    private static final Logger logger = LoggerFactory.getLogger(WalletController.class);
    @Autowired
    WalletService walletService;

    @Autowired
    TransactionService transactionService;
    @Override
    @Transactional
    public WalletDTO createWallet(WalletDTO dto) {
        var newWallet = walletService.createWallet(WalletDTO.toEntity(dto));
        logger.info("wallet created successfully {}", dto.toString());
        return WalletDTO.toDto(newWallet);
    }
    @Override
    @Transactional
    public BalanceDTO getWalletBalance(UUID id) {
        var balance = walletService.getWalletBalance(id);
        logger.info("balance found successfully for wallet {}", id);
        return new BalanceDTO(balance);
    }
    @Override
    @Transactional(rollbackOn = Exception.class)
    public WalletDTO deposit(DepositDTO dto) {
        var updatedWallet = walletService.deposit(dto.operation().idWallet(),
                                                         dto.operation().amount());
        transactionService.createTransaction(updatedWallet, null, dto.operation(), TransactionType.DEPOSIT);

        logger.info("deposit done successfully {}", dto.toString());
        return WalletDTO.toDto(updatedWallet);
    }
    @Override
    @Transactional(rollbackOn = Exception.class)
    public WalletDTO withdraw(WithdrawDTO dto) {
        var updatedWallet = walletService.withdraw(dto.operation().idWallet(),
                                                          dto.operation().amount());
        transactionService.createTransaction(updatedWallet, null, dto.operation(), TransactionType.WITHDRAWAL);

        logger.info("withdraw done successfully {}", dto.toString());
        return WalletDTO.toDto(updatedWallet);
    }
    @Override
    public List<HistoricalBalanceDTO> getHistoricalBalance(UUID id) {
        List<Transaction> transactions = transactionService.getTransactionsByWallet(walletService.getWallet(id));

        logger.info("historical balance done successfully for wallet {}", id);
        return HistoricalBalanceDTO.toDto(transactions);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public HistoricalBalanceDTO transfer(TransferOperationDTO dto) {
        var withdrawWallet = walletService.withdraw(dto.operation().idWallet(), dto.operation().amount());
        var depositWallet = walletService.deposit(dto.destinationWallet(), dto.operation().amount());

        var withdrawTransaction = transactionService.createTransaction(withdrawWallet, depositWallet, dto.operation(), TransactionType.TRANSFER_OUT);
        transactionService.createTransaction(depositWallet, withdrawWallet, dto.operation(), TransactionType.TRANSFER_IN);

        logger.info("transfer done successfully {}", dto.toString());
        return HistoricalBalanceDTO.toDto(withdrawTransaction);
    }
}
