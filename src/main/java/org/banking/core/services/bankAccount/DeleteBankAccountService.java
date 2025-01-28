package org.banking.core.services.bankAccount;

import org.banking.core.database.JpaBankAccountRepository;
import org.banking.core.request.bankAccount.DeleteBankAccountRequest;
import org.banking.core.response.CoreError;
import org.banking.core.response.bankAccount.DeleteBankAccountResponse;
import org.banking.core.services.validators.bankAccountValidators.DeleteBankAccountValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeleteBankAccountService {

    @Autowired
    private JpaBankAccountRepository bankAccountRepository;

    @Autowired
    private DeleteBankAccountValidator validator;

    private static final Logger logger = LoggerFactory.getLogger(DeleteBankAccountService.class);

    public DeleteBankAccountResponse execute(DeleteBankAccountRequest request) {
        logger.info("Received request to delete a bank account with personal code: {}", request.getPersonalCode());

        logger.debug("Validating delete request: {}", request);
        List<CoreError> errorList = validator.validate(request);

        if (errorList.isEmpty()) {
            logger.info("Validation successful for delete request: {}", request);

            logger.info("Deleting bank account with personal code: {}", request.getPersonalCode());
            bankAccountRepository.deleteByPersonalCode(request.getPersonalCode());

            logger.info("Successfully deleted bank account with personal code: {}", request.getPersonalCode());
            return new DeleteBankAccountResponse(true);
        } else {
            logger.warn("Validation failed for delete request: {}. Errors: {}", request, errorList);
            return new DeleteBankAccountResponse(errorList);
        }
    }
}
