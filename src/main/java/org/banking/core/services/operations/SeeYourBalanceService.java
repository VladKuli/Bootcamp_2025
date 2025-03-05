package org.banking.core.services.operations;

import org.banking.core.database.JpaBankAccountRepository;
import org.banking.core.domain.BankAccount;
import org.banking.core.domain.Card;
import org.banking.core.request.operations.SeeYourBalanceRequest;
import org.banking.core.response.operations.SeeYourBalanceResponse;
import org.banking.core.services.bankAccount.GetCurrentBankAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SeeYourBalanceService {

    @Autowired
    private GetCurrentBankAccountService getCurrentBankAccount;

    @Autowired
    private JpaBankAccountRepository jpaBankAccountRepository;

    private static final Logger logger = LoggerFactory.getLogger(SeeYourBalanceService.class);

    public SeeYourBalanceResponse execute(SeeYourBalanceRequest request) {
        logger.info("Received request to see balance for the current user");

        Optional<BankAccount> bankAccount = countBalance();

        return new SeeYourBalanceResponse(bankAccount);
    }

    private Optional<BankAccount> countBalance() {

        Optional<BankAccount> bankAccount = getCurrentBankAccount.get();
            int balance = 0 ;
            List<Card> cardList =  bankAccount.get().getCards();
            for (Card card : cardList) {
                balance += card.getBalance();
        }
        jpaBankAccountRepository.updateBalance(bankAccount.get().getId(),balance);
        return Optional.of(BankAccount.builder()
                 .name(bankAccount.get().getName())
                 .surname(bankAccount.get().getSurname())
                 .personalCode(bankAccount.get().getPersonalCode())
                 .balance(balance)
                 .IBAN(bankAccount.get().getIBAN())
                 .cards(bankAccount.get().getCards())
                 .outgoingTransactions(bankAccount.get().getOutgoingTransactions())
                 .incomingTransactions(bankAccount.get().getIncomingTransactions())
                 .build());
    }
}
