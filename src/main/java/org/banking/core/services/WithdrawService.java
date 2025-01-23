package org.banking.core.services;

import org.banking.core.database.JpaBankAccountRepository;
import org.banking.core.request.WithdrawRequest;
import org.banking.core.response.CoreError;
import org.banking.core.response.WithdrawResponse;
import org.banking.core.services.validators.WithdrawValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class WithdrawService {

    @Autowired
    private JpaBankAccountRepository bankAccountRepository;

    @Autowired
    private WithdrawValidator validator;

    public WithdrawResponse execute(WithdrawRequest request) {
        List<CoreError> errorList = validator.validate(request);

        if (errorList.isEmpty()) {
            bankAccountRepository.withdraw(request.getPersonalCode(),request.getAmount());

            return new WithdrawResponse(true);
        } else {
            return new WithdrawResponse(errorList);
        }
    }
}
