package org.banking.core.services.bankAccount;

import org.banking.core.database.JpaBankAccountRepository;
import org.banking.core.domain.BankAccount;
import org.banking.core.request.bankAccount.AddBankAccountRequest;
import org.banking.core.response.bankAccount.AddBankAccountResponse;
import org.banking.core.response.CoreError;
import org.banking.core.services.validators.bankAccountValidators.AddBankAccountValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddBankAccountService {


    @Autowired
    private JpaBankAccountRepository bankAccountRepository;

    @Autowired
    private AddBankAccountValidator validator;

    public AddBankAccountResponse execute(AddBankAccountRequest request) {
        List<CoreError> errorList = validator.validate(request);

        if (errorList.isEmpty()) {
            BankAccount bankAccount = BankAccount.builder()
                    .name(request.getName())
                    .surname(request.getSurname())
                    .personalCode(request.getPersonalCode())
                    .build();

            bankAccountRepository.save(bankAccount);

            return new AddBankAccountResponse(bankAccount);
        } else {
            return new AddBankAccountResponse(errorList);
        }
    }
}
