package org.banking.core.services;

import org.banking.core.database.JpaBankAccountRepository;
import org.banking.core.request.DepositRequest;
import org.banking.core.response.CoreError;
import org.banking.core.response.DepositResponse;
import org.banking.core.services.validators.DepositValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DepositService {

    @Autowired
    private JpaBankAccountRepository bankAccountRepository;

    @Autowired
    private DepositValidator validator;

    public DepositResponse execute(DepositRequest request) {
        List<CoreError> errorList = validator.validate(request);

        if (errorList.isEmpty()) {
            bankAccountRepository.deposit(request.getPersonalCode(),request.getAmount());

            return new DepositResponse(true);
        } else {
            return new DepositResponse(errorList);
        }
    }
}
