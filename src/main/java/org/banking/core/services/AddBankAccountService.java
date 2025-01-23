package org.banking.core.services;

import liquibase.pro.packaged.A;
import org.banking.core.database.JpaBankAccountRepository;
import org.banking.core.domain.BankAccount;
import org.banking.core.request.AddBankAccountRequest;
import org.banking.core.response.AddBankAccountResponse;
import org.banking.core.response.CoreError;
import org.banking.core.services.validators.AddBankAccountValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddBankAccountService {


    @Autowired
    private JpaBankAccountRepository bankAccountRepository;

    @Autowired
    private AddBankAccountValidator validator;

    public AddBankAccountResponse execute(AddBankAccountRequest request) {
        List<CoreError> errorList = validator.validate(request);

        if (errorList.isEmpty()) {
            BankAccount bankAccount = new BankAccount(request.getName(), request.getSurname(),
                    request.getPersonalCode());

            bankAccountRepository.save(bankAccount);

            return new AddBankAccountResponse(bankAccount);
        } else {
            return new AddBankAccountResponse(errorList);
        }
    }
}
