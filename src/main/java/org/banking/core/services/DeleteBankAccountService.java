package org.banking.core.services;

import liquibase.pro.packaged.C;
import org.banking.core.database.JpaBankAccountRepository;
import org.banking.core.database.JpaUserRepository;
import org.banking.core.request.DeleteBankAccountRequest;
import org.banking.core.response.CoreError;
import org.banking.core.response.DeleteBankAccountResponse;
import org.banking.core.services.validators.DeleteBankAccountValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeleteBankAccountService {

    @Autowired
    private JpaBankAccountRepository bankAccountRepository;
    @Autowired
    private JpaUserRepository userRepository;
    @Autowired
    private DeleteBankAccountValidator validator;

    public DeleteBankAccountResponse execute(DeleteBankAccountRequest request) {
        List<CoreError> errorList = validator.validate(request);

        if (errorList.isEmpty()) {
            bankAccountRepository.deleteByPersonalCode(request.getPersonalCode());
            userRepository.deleteByPersonalCode(request.getPersonalCode());
            return new DeleteBankAccountResponse(true);
        } else {
            return new DeleteBankAccountResponse(errorList);
        }
    }
}
