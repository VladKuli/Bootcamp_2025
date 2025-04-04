package org.banking.core.services.bankAccount;

import org.banking.core.database.JpaBankAccountRepository;
import org.banking.core.domain.BankAccount;
import org.banking.core.domain.IBAN;
import org.banking.core.domain.Transaction;
import org.banking.core.dto.bank_account.BankAccountDTO;
import org.banking.core.dto.iban.IbanDTO;
import org.banking.core.dto.transaction.TransactionDTO;
import org.banking.core.mapper.bank_account.BankAccountMapper;
import org.banking.core.mapper.iban.IbanMapper;
import org.banking.core.mapper.transaction.TransactionMapper;
import org.banking.core.response.bankAccount.GetCurrentBankAccountResponse;
import org.banking.core.services.user.GetCurrentUserPersonalCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
//TODO MAKE REFACTORING FOR THIS CLASS (IBAN USER)
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
