package org.banking.core.services.operations;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.banking.core.database.JpaBankAccountRepository;
import org.banking.core.database.JpaTransactionRepository;
import org.banking.core.domain.BankAccount;
import org.banking.core.domain.Transaction;
import org.banking.core.request.operations.MoneyTransferRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MoneyTransferProcessService {

    private final JpaTransactionRepository transactionRepository;

    private final JpaBankAccountRepository bankAccountRepository;


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
            log.info("Initiating money transfer from {} to {} with amount: {}",
                    userBankAccount.get().getIBAN(), request.getTargetIBAN(), request.getAmount());

            bankAccountRepository.bankTransfer(request.getUsersIban(), request.getTargetIBAN(), request.getAmount());
        } else {
            //TODO WRITE CUSTOM EXCEPTION
            log.warn("Error Bank Account is null");
        }
    }
}
