package org.banking.core.services.operations;

import org.banking.core.database.JpaBankAccountRepository;
import org.banking.core.request.operations.MoneyTransferRequest;
import org.banking.core.response.operations.MoneyTransferResponse;
import org.banking.core.services.user.GetCurrentUserPersonalCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MoneyTransferService {

    @Autowired
    private JpaBankAccountRepository bankAccountRepository;
    @Autowired
    private GetCurrentUserPersonalCodeService personalCodeService;

    public MoneyTransferResponse execute(MoneyTransferRequest request) {
        bankAccountRepository.bankTransfer(personalCodeService.getCurrentUserPersonalCode(),
                request.getTargetPersonalCode(), request.getAmount());
        return new MoneyTransferResponse(true);
    }
}

