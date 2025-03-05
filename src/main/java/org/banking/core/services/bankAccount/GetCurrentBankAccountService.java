package org.banking.core.services.bankAccount;

import org.banking.core.database.JpaBankAccountRepository;
import org.banking.core.domain.BankAccount;
import org.banking.core.services.user.GetCurrentUserPersonalCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetCurrentBankAccountService {

    @Autowired
    private JpaBankAccountRepository jpa;

    @Autowired
    GetCurrentUserPersonalCodeService getUser;

    public Optional<BankAccount> execute() {

        String personalCode = getUser.getCurrentUserPersonalCode();

        return !jpa.findByPersonalCode(personalCode).isEmpty()
                ? jpa.findByPersonalCode(personalCode).stream().findFirst()
                : Optional.empty();
    }
}
