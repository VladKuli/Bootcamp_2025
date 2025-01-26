package org.banking.core.services.bankAccount;

import org.banking.core.database.JpaBankAccountRepository;
import org.banking.core.request.bankAccount.DeleteBankAccountRequest;
import org.banking.core.response.CoreError;
import org.banking.core.response.bankAccount.DeleteBankAccountResponse;
import org.banking.core.services.validators.bankAccountValidators.DeleteBankAccountValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeleteBankAccountService {

    @Autowired
    private JpaBankAccountRepository bankAccountRepository;
    @Autowired
    private DeleteBankAccountValidator validator;

    public DeleteBankAccountResponse execute(DeleteBankAccountRequest request) {
        List<CoreError> errorList = validator.validate(request);

        if (errorList.isEmpty()) {

            bankAccountRepository.deleteByPersonalCode(request.getPersonalCode());

            return new DeleteBankAccountResponse(true);
        } else {

            return new DeleteBankAccountResponse(errorList);
        }
    }
}
