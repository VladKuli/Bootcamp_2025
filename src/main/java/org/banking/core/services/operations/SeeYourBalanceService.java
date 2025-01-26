package org.banking.core.services.operations;

import org.banking.core.database.JpaBankAccountRepository;
import org.banking.core.domain.BankAccount;
import org.banking.core.request.operations.SeeYourBalanceRequest;
import org.banking.core.response.operations.SeeYourBalanceResponse;
import org.banking.core.services.user.GetCurrentUserPersonalCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SeeYourBalanceService {

    @Autowired
    private JpaBankAccountRepository bankAccountRepository;

    @Autowired
    private GetCurrentUserPersonalCodeService personalCodeService;

    public SeeYourBalanceResponse execute(SeeYourBalanceRequest request) {
        String personalCode = personalCodeService.getCurrentUserPersonalCode();

        Optional<BankAccount> bankAccount = bankAccountRepository.seeYourBalance(personalCode);

        return new SeeYourBalanceResponse(bankAccount);
    }

}
