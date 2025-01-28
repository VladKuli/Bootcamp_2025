package org.banking.core.services.operations;

import org.banking.core.database.JpaBankAccountRepository;
import org.banking.core.domain.BankAccount;
import org.banking.core.request.operations.SeeYourBalanceRequest;
import org.banking.core.response.operations.SeeYourBalanceResponse;
import org.banking.core.services.user.GetCurrentUserPersonalCodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SeeYourBalanceService {

    @Autowired
    private JpaBankAccountRepository bankAccountRepository;

    @Autowired
    private GetCurrentUserPersonalCodeService personalCodeService;

    private static final Logger logger = LoggerFactory.getLogger(SeeYourBalanceService.class);

    public SeeYourBalanceResponse execute(SeeYourBalanceRequest request) {
        logger.info("Received request to see balance for the current user");

        logger.debug("Fetching personal code for the current user");
        String personalCode = personalCodeService.getCurrentUserPersonalCode();
        logger.info("Personal code retrieved: {}", personalCode);

        logger.debug("Fetching balance for personal code: {}", personalCode);
        Optional<BankAccount> bankAccount = bankAccountRepository.seeYourBalance(personalCode);

        if (bankAccount.isPresent()) {
            logger.info("Successfully retrieved balance for personal code: {}. Balance: {}",
                    personalCode, bankAccount.get().getBalance());
        } else {
            logger.warn("No bank account found for personal code: {}", personalCode);
        }

        return new SeeYourBalanceResponse(bankAccount);
    }
}
