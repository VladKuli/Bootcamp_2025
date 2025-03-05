package org.banking.core.services.operations;

import org.banking.core.database.JpaBankAccountRepository;
import org.banking.core.database.JpaTransactionRepository;
import org.banking.core.domain.BankAccount;
import org.banking.core.domain.Transaction;
import org.banking.core.request.operations.MoneyTransferRequest;
import org.banking.core.response.CoreError;
import org.banking.core.response.bankAccount.GetCurrentBankAccountResponse;
import org.banking.core.response.operations.MoneyTransferResponse;
import org.banking.core.services.bankAccount.GetCurrentBankAccountService;
import org.banking.core.services.validators.operationsValidators.MoneyTransferValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MoneyTransferService {

    @Autowired
    private JpaBankAccountRepository bankAccountRepository;

    @Autowired
    private MoneyTransferValidator validator;
    @Autowired
    private JpaTransactionRepository transactionRepository;

    @Autowired
    private GetCurrentBankAccountService getCurrentBankAccount;

    private static final Logger logger = LoggerFactory.getLogger(MoneyTransferService.class);

    public MoneyTransferResponse execute(MoneyTransferRequest request) {
        logger.info("Received money transfer request from current user to target personal code: {} with amount: {}",
                request.getTargetIBAN(), request.getAmount());

        logger.debug("Validating money transfer request: {}", request);
        List<CoreError> errorList = validator.execute(request);

        if (errorList.isEmpty()) {
            Optional<BankAccount> userBankAccount = getCurrentBankAccount.get();
            logger.info("Validation successful for money transfer request: {}", request);

            logger.debug("Retrieved sender personal code: {}", userBankAccount.get().getIBAN());

            logger.info("Initiating money transfer from {} to {} with amount: {}",
                    userBankAccount.get().getIBAN(), request.getTargetIBAN(), request.getAmount());

            try {

                // Use the TransactionService to perform the actual money transfer
                bankAccountRepository.bankTransfer(userBankAccount.get().getIBAN(), request.getTargetIBAN(), request.getAmount()
                );
                addTransaction(request, userBankAccount.get());

                logger.info("Money transfer successful from {} to {} with amount: {}",
                        userBankAccount.get().getIBAN(), request.getTargetIBAN(), request.getAmount());

                return new MoneyTransferResponse(true);

            } catch (RuntimeException e) {
                logger.error("Money transfer failed: {}", e.getMessage());
                return new MoneyTransferResponse(false);
            }

        } else {
            logger.warn("Validation failed for money transfer request: {}. Errors: {}", request, errorList);
            return new MoneyTransferResponse(errorList);
        }
    }

    private Optional<BankAccount> findPayeeBankAccount(MoneyTransferRequest request) {
        return bankAccountRepository.findByIBAN(request.getTargetIBAN()).stream().findFirst();
    }

    private void addTransaction(MoneyTransferRequest request, BankAccount userBankAccount ) {
        transactionRepository.save(Transaction.builder()
                .toAccount(findPayeeBankAccount(request).get())
                .fromAccount(userBankAccount)
                .type(request.getType())
                .description(request.getDescription())
                .amount(request.getAmount()).build());
    }
}
