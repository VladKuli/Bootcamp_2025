package org.banking.core.services.bankAccount;

import org.banking.core.database.JpaBankAccountRepository;
import org.banking.core.domain.BankAccount;
import org.banking.core.request.bankAccount.AddBankAccountRequest;
import org.banking.core.response.bankAccount.AddBankAccountResponse;
import org.banking.core.response.CoreError;
import org.banking.core.services.validators.bankAccountValidators.AddBankAccountValidator;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import org.slf4j.Logger;

@Service
public class AddBankAccountService {

    @Autowired
    private JpaBankAccountRepository bankAccountRepository;

    private static final Logger logger = LoggerFactory.getLogger(AddBankAccountService.class);

    @Autowired
    private AddBankAccountValidator validator;

    public AddBankAccountResponse execute(AddBankAccountRequest request) {
        logger.info("Received request to add a new bank account: {}", request);

        logger.debug("Validating request: {}", request);
        List<CoreError> errorList = validator.validate(request);

        if (errorList.isEmpty()) {
            logger.info("Validation successful for request: {}", request);

            logger.debug("Building BankAccount entity for request: {}", request);
            BankAccount bankAccount = BankAccount.builder()
                    .name(request.getName())
                    .surname(request.getSurname())
                    .personalCode(request.getPersonalCode())
                    .build();

            logger.info("Saving BankAccount to the database: {}", bankAccount);
            bankAccountRepository.save(bankAccount);

            logger.info("Successfully added bank account: {}", bankAccount);
            return new AddBankAccountResponse(bankAccount);
        } else {

            logger.warn("Validation failed for request: {}. Errors: {}", request, errorList);
            return new AddBankAccountResponse(errorList);
        }
    }
}
