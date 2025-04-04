package org.banking.core.services.operations;

import org.banking.core.database.JpaBankAccountRepository;
import org.banking.core.database.JpaTransactionRepository;
import org.banking.core.domain.BankAccount;
import org.banking.core.domain.Transaction;
import org.banking.core.request.operations.MoneyTransferRequest;
import org.banking.core.services.bankAccount.GetCurrentBankAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MoneyTransferProcessService {

    @Autowired
    private JpaTransactionRepository transactionRepository;

    @Autowired
    private JpaBankAccountRepository bankAccountRepository;

    private static final Logger logger = LoggerFactory.getLogger(MoneyTransferProcessService.class);

    public void execute(MoneyTransferRequest request, Optional<BankAccount> userBankAccount) {

        if(userBankAccount.isPresent()) {
            addTransaction(request,userBankAccount.get());
            updateBalance(request,userBankAccount);
        }

    }

    private Optional<BankAccount> findPayeeBankAccount(MoneyTransferRequest request) {
        return bankAccountRepository.findByIBANNumber(request.getTargetIBAN()).stream().findFirst();
    }

    private void addTransaction(MoneyTransferRequest request, BankAccount userBankAccount) {
        transactionRepository.save(Transaction.builder()
                .toAccount(findPayeeBankAccount(request).get())
                .fromAccount(userBankAccount)
                .type(request.getType())
                .description(request.getDescription())
                .amount(request.getAmount()).build());
    }

    private void updateBalance(MoneyTransferRequest request, Optional<BankAccount> userBankAccount) {
        if (userBankAccount.isPresent()) {
            logger.info("Initiating money transfer from {} to {} with amount: {}",
                    userBankAccount.get().getIBAN(), request.getTargetIBAN(), request.getAmount());

            bankAccountRepository.bankTransfer(request.getUsersIban(), request.getTargetIBAN(), request.getAmount());
        } else {
            //TODO WRITE CUSTOM EXCEPTION
            logger.warn("Error Bank Account is null");
        }
    }
}
