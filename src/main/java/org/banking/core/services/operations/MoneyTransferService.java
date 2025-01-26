package org.banking.core.services.operations;

import org.banking.core.database.JpaBankAccountRepository;
import org.banking.core.request.operations.MoneyTransferRequest;
import org.banking.core.response.CoreError;
import org.banking.core.response.operations.MoneyTransferResponse;
import org.banking.core.services.user.GetCurrentUserPersonalCodeService;
import org.banking.core.services.validators.operationsValidators.MoneyTransferValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MoneyTransferService {

    @Autowired
    private JpaBankAccountRepository bankAccountRepository;
    @Autowired
    private GetCurrentUserPersonalCodeService personalCodeService;
    @Autowired
    private MoneyTransferValidator validator;


    public MoneyTransferResponse execute(MoneyTransferRequest request) {
        List<CoreError> errorList = validator.execute(request);

        if (errorList.isEmpty()) {
            bankAccountRepository.bankTransfer(personalCodeService.getCurrentUserPersonalCode(),
                    request.getTargetPersonalCode(), request.getAmount());
            return new MoneyTransferResponse(true);

        } else {
            return new MoneyTransferResponse(errorList);
        }

    }
}

