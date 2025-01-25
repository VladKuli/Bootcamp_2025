package org.banking.core.services.operations;

import org.banking.core.database.JpaBankAccountRepository;
import org.banking.core.request.operations.DepositRequest;
import org.banking.core.response.CoreError;
import org.banking.core.response.operations.DepositResponse;
import org.banking.core.services.user.GetCurrentUserPersonalCodeService;
import org.banking.core.services.validators.DepositValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepositService {

    @Autowired
    private JpaBankAccountRepository bankAccountRepository;

    @Autowired
    private DepositValidator validator;
    @Autowired
    private GetCurrentUserPersonalCodeService personalCodeService;

    public DepositResponse execute(DepositRequest request) {
        List<CoreError> errorList = validator.validate(request);

        if (errorList.isEmpty()) {
            String personalCode = personalCodeService.getCurrentUserPersonalCode();
            bankAccountRepository.deposit(personalCode,request.getAmount());

            return new DepositResponse(true);
        } else {

            return new DepositResponse(errorList);
        }
    }
}
