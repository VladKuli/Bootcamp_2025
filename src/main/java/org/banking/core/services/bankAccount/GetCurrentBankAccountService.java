package org.banking.core.services.bankAccount;

import org.banking.core.database.JpaBankAccountRepository;
import org.banking.core.domain.BankAccount;
import org.banking.core.domain.IBAN;
import org.banking.core.dto.iban.IbanDTO;
import org.banking.core.mapper.iban.IbanMapper;
import org.banking.core.response.bankAccount.GetCurrentBankAccountResponse;
import org.banking.core.services.user.GetCurrentUserPersonalCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GetCurrentBankAccountService {

    @Autowired
    private JpaBankAccountRepository jpa;

    @Autowired
    private GetCurrentUserPersonalCodeService getUser;

    @Autowired
    private IbanMapper ibanMapper;

    public Optional<BankAccount> get() {

        String personalCode = getUser.getCurrentUserPersonalCode();

        return !jpa.findByPersonalCode(personalCode).isEmpty()
                ? jpa.findByPersonalCode(personalCode).stream().findFirst()
                : Optional.empty();
    }

    public List<IBAN> getIBAN() {
        String personalCode = getUser.getCurrentUserPersonalCode();

        return jpa.findByPersonalCode(personalCode).stream()
                .flatMap(bankAccount -> bankAccount.getIBAN().stream())
                .toList();
    }


    public List<IbanDTO> getIbanDTO() {
        String personalCode = getUser.getCurrentUserPersonalCode();

        List<IBAN> ibanList = jpa.findByPersonalCode(personalCode).stream()
                .flatMap(bankAccount -> bankAccount.getIBAN().stream())
                .toList();

        return ibanList.stream()
                .map(iban -> ibanMapper.toDto(iban)).collect(Collectors.toList());
    }

}
