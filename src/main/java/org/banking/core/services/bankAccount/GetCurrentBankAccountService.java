package org.banking.core.services.bankAccount;

import org.banking.core.database.JpaBankAccountRepository;
import org.banking.core.domain.BankAccount;
import org.banking.core.dto.bank_account.BankAccountDTO;
import org.banking.core.mapper.bank_account.BankAccountMapper;
import org.banking.core.services.user.GetCurrentUserPersonalCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class GetCurrentBankAccountService {

    @Autowired
    private JpaBankAccountRepository jpa;

    @Autowired
    private GetCurrentUserPersonalCodeService getUser;

    @Autowired
    private BankAccountMapper bankAccountMapper;


    public Optional<BankAccount> get() {

        String personalCode = getUser.getCurrentUserPersonalCode();

        return !jpa.findByPersonalCode(personalCode).isEmpty()
                ? jpa.findByPersonalCode(personalCode).stream().findFirst()
                : Optional.empty();
    }


    public BankAccountDTO getBankAccountDTO() {

        String personalCode = getUser.getCurrentUserPersonalCode();

        Optional<BankAccount> bankAccount = Optional.empty();
        if (!jpa.findByPersonalCode(personalCode).isEmpty()) {
            bankAccount = jpa.findByPersonalCode(personalCode).stream().findFirst();
        }

        return bankAccountMapper.toDto(bankAccount.get());
    }

}
