package org.banking.core.services.operations;

import org.banking.core.domain.BankAccount;
import org.banking.core.request.operations.SeeYourBalanceRequest;
import org.banking.core.response.operations.SeeYourBalanceResponse;
import org.banking.core.services.bankAccount.GetCurrentBankAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class SeeYourBalanceService {

    @Autowired
    private GetCurrentBankAccountService getCurrentBankAccountService;

    private static final Logger logger = LoggerFactory.getLogger(SeeYourBalanceService.class);

    public SeeYourBalanceResponse execute(SeeYourBalanceRequest request) {
        logger.info("Received request to see balance for the current user");
        Optional<BankAccount> bankAccount = getCurrentBankAccountService.get();

        return new SeeYourBalanceResponse(bankAccount);
    }
}