package org.banking.core.services;

import org.banking.core.database.JpaBankAccountRepository;
import org.banking.core.request.MoneyTransferRequest;
import org.banking.core.response.MoneyTransferResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
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
