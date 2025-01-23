package org.banking.core.services;

import org.banking.core.database.JpaBankAccountRepository;
import org.banking.core.database.JpaUserRepository;
import org.banking.core.domain.BankAccount;
import org.banking.core.request.AddBankAccountRequest;
import org.banking.core.request.DeleteBankAccountRequest;
import org.banking.core.request.DeleteUserRequest;
import org.banking.core.response.AddBankAccountResponse;
import org.banking.core.response.CoreError;
import org.banking.core.response.DeleteBankAccountResponse;
import org.banking.core.response.DeleteUserResponse;
import org.banking.core.services.validators.AddBankAccountValidator;
import org.banking.core.services.validators.DeleteBankAccountValidator;
import org.banking.core.services.validators.DeleteUserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeleteUserService {

    @Autowired
    private JpaBankAccountRepository bankAccountRepository;
    @Autowired
    private JpaUserRepository userRepository;
    @Autowired
    private DeleteUserValidator validator;

    public DeleteUserResponse execute(DeleteUserRequest request) {
        List<CoreError> errorList = validator.validate(request);

        if (errorList.isEmpty()) {
            userRepository.deleteByPersonalCode(request.getPersonalCode());
            bankAccountRepository.deleteByPersonalCode(request.getPersonalCode());
            return new DeleteUserResponse(true);
        } else {
            return new DeleteUserResponse(errorList);
        }
    }
}
