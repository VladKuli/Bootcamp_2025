package org.banking.core.services.operations;

import org.banking.core.database.JpaBankAccountRepository;
import org.banking.core.database.JpaTransactionRepository;
import org.banking.core.domain.BankAccount;
import org.banking.core.domain.Transaction;
import org.banking.core.request.operations.MoneyTransferRequest;
import org.banking.core.response.CoreError;
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
    private GetCurrentBankAccountService getCurrentBankAccount;

    @Autowired
    private MoneyTransferValidator validator;

    @Autowired
    private MoneyTransferProcessService processService;


    private static final Logger logger = LoggerFactory.getLogger(MoneyTransferService.class);

    public MoneyTransferResponse execute(MoneyTransferRequest request) {
        logger.info("Received money transfer request from current user to target personal code: {} with amount: {}",
                request.getTargetIBAN(), request.getAmount());

        logger.debug("Validating money transfer request: {}", request);
        List<CoreError> errorList = validator.execute(request);

        if (errorList.isEmpty()) {
            logger.info("Validation successful for money transfer request: {}", request);

            Optional<BankAccount> userBankAccount = getCurrentBankAccount.get();
            logger.debug("Retrieved sender personal code: {}", userBankAccount.get().getIBAN());


            processService.execute(request,userBankAccount);

                logger.info("Money transfer successful from {} to {} with amount: {}",
                        userBankAccount.get().getIBAN(), request.getTargetIBAN(), request.getAmount());

                return new MoneyTransferResponse(true);
        }
            logger.warn("Validation failed for money transfer request: {}. Errors: {}", request, errorList);
            return new MoneyTransferResponse(errorList);
    }

}