package org.banking.core.services.operations;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.banking.core.domain.BankAccount;
import org.banking.core.request.operations.MoneyTransferRequest;
import org.banking.core.response.CoreError;
import org.banking.core.response.operations.MoneyTransferResponse;
import org.banking.core.services.bankAccount.GetCurrentBankAccountService;
import org.banking.core.services.validators.operationsValidators.MoneyTransferValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Slf4j
@Service
@RequiredArgsConstructor
public class MoneyTransferService {

    private final GetCurrentBankAccountService getCurrentBankAccount;

    private final MoneyTransferValidator validator;

    private final MoneyTransferProcessService processService;

    public MoneyTransferResponse execute(MoneyTransferRequest request) {
        log.info("Received money transfer request from current user to target personal code: {} with amount: {}",
                request.getTargetIBAN(), request.getAmount());

        log.debug("Validating money transfer request: {}", request);
        List<CoreError> errorList = validator.execute(request);

        if (errorList.isEmpty()) {
            log.info("Validation successful for money transfer request: {}", request);

            Optional<BankAccount> userBankAccount = getCurrentBankAccount.get();
            log.debug("Retrieved sender personal code: {}", userBankAccount.get().getIBAN());


            processService.execute(request,userBankAccount);

                log.info("Money transfer successful from {} to {} with amount: {}",
                        userBankAccount.get().getIBAN(), request.getTargetIBAN(), request.getAmount());

                return new MoneyTransferResponse(true);
        }
            log.warn("Validation failed for money transfer request: {}. Errors: {}", request, errorList);
            return new MoneyTransferResponse(errorList);
    }

}