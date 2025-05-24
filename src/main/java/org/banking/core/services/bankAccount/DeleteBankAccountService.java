package org.banking.core.services.bankAccount;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.banking.core.database.JpaBankAccountRepository;
import org.banking.core.request.bankAccount.DeleteBankAccountRequest;
import org.banking.core.response.CoreError;
import org.banking.core.response.bankAccount.DeleteBankAccountResponse;
import org.banking.core.services.validators.bankAccountValidators.DeleteBankAccountValidator;

import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeleteBankAccountService {

    private final JpaBankAccountRepository bankAccountRepository;

    private final DeleteBankAccountValidator validator;

    public DeleteBankAccountResponse execute(DeleteBankAccountRequest request) {
        log.info("Received request to delete a bank account with personal code: {}", request.getPersonalCode());

        log.debug("Validating delete request: {}", request);
        List<CoreError> errorList = validator.validate(request);

        if (errorList.isEmpty()) {
            log.info("Validation successful for delete request: {}", request);

            log.info("Deleting bank account with personal code: {}", request.getPersonalCode());
            bankAccountRepository.deleteByPersonalCode(request.getPersonalCode());

            log.info("Successfully deleted bank account with personal code: {}", request.getPersonalCode());
            return new DeleteBankAccountResponse(true);
        } else {
            log.warn("Validation failed for delete request: {}. Errors: {}", request, errorList);
            return new DeleteBankAccountResponse(errorList);
        }
    }
}
