package org.banking.core.services.operations;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.banking.core.domain.BankAccount;
import org.banking.core.dto.bank_account.BankAccountDTO;
import org.banking.core.mapper.bank_account.BankAccountMapper;
import org.banking.core.request.operations.SeeYourBalanceRequest;
import org.banking.core.response.operations.SeeYourBalanceResponse;
import org.banking.core.services.bankAccount.GetCurrentBankAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import java.util.Optional;
@Slf4j
@Service
@RequiredArgsConstructor
public class SeeYourBalanceService {

    private final GetCurrentBankAccountService getCurrentBankAccountService;

    private final BankAccountMapper bankAccountMapper;

    public SeeYourBalanceResponse execute(SeeYourBalanceRequest request) {
        log.info("Received request to see balance for the current user");
        Optional<BankAccount> bankAccount = getCurrentBankAccountService.get();

        BankAccountDTO bankAccountDTO = bankAccountMapper.toDto(bankAccount.get());

        return new SeeYourBalanceResponse(bankAccountDTO);
    }
}