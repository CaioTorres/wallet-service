package com.wallet_service.usecase;

import com.wallet_service.domain.dto.*;

import java.util.List;
import java.util.UUID;

public interface WalletOperationUseCase {
    WalletDTO createWallet(WalletDTO dto);
    BalanceDTO getWalletBalance(UUID id);
    WalletDTO deposit(DepositDTO dto);
    WalletDTO withdraw(WithdrawDTO dto);
    List<HistoricalBalanceDTO> getHistoricalBalance(UUID id);

    HistoricalBalanceDTO transfer(TransferOperationDTO dto);



}
