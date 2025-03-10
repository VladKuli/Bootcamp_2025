package org.banking.core.services.operations;

import org.banking.core.database.JpaBankAccountRepository;
import org.banking.core.domain.BankAccount;
import org.banking.core.domain.Card;
import org.banking.core.domain.IBAN;
import org.banking.core.request.operations.SeeYourBalanceRequest;
import org.banking.core.response.operations.SeeYourBalanceResponse;
import org.banking.core.services.bankAccount.GetCurrentBankAccountService;
import org.banking.core.services.user.GetCurrentUserPersonalCodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class SeeYourBalanceService {

    @Autowired
    private GetCurrentBankAccountService getCurrentBankAccountService;

    @Autowired
    private JpaBankAccountRepository jpaBankAccountRepository;

    private static final Logger logger = LoggerFactory.getLogger(SeeYourBalanceService.class);

    public SeeYourBalanceResponse execute(SeeYourBalanceRequest request) {
        logger.info("Received request to see balance for the current user");

        Optional<BankAccount> bankAccount = countBalance();

        return new SeeYourBalanceResponse(bankAccount);
    }

    //TODO write DTO for it to hide information
    private Optional<BankAccount> countBalance() {
        int balance = 0;
        Optional<BankAccount> bankAccount = getCurrentBankAccountService.get();

        List<IBAN> ibanList = bankAccount
                .map(BankAccount::getIBAN)
                .orElse(Collections.emptyList());
        balance = ibanList.stream()
                .mapToInt(IBAN::getBalance)
                .sum();


        jpaBankAccountRepository.updateBalance(bankAccount.get().getId(), balance);


        return Optional.of(BankAccount.builder()
                .name(bankAccount.get().getName())
                .surname(bankAccount.get().getSurname())
                .personalCode(bankAccount.get().getPersonalCode())
                .balance(balance).build());
    }
}