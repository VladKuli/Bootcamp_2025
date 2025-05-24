package org.banking.core.services.bankAccount;

import lombok.RequiredArgsConstructor;
import org.banking.core.database.JpaBankAccountRepository;
import org.banking.core.domain.BankAccount;
import org.banking.core.dto.bank_account.BankAccountDTO;
import org.banking.core.mapper.bank_account.BankAccountMapper;
import org.banking.core.services.user.GetCurrentUserPersonalCodeService;
import org.springframework.stereotype.Service;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class GetCurrentBankAccountService {

    private final JpaBankAccountRepository jpa;

    private final GetCurrentUserPersonalCodeService getUser;

    private final BankAccountMapper bankAccountMapper;


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
