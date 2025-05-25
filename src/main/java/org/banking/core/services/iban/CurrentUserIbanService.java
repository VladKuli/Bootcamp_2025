package org.banking.core.services.iban;

import lombok.RequiredArgsConstructor;
import org.banking.core.database.JpaBankAccountRepository;
import org.banking.core.domain.Card;
import org.banking.core.domain.IBAN;
import org.banking.core.dto.card.CardDTO;
import org.banking.core.dto.iban.IbanDTO;
import org.banking.core.mapper.iban.IbanMapper;
import org.banking.core.services.user.GetCurrentUserPersonalCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CurrentUserIbanService {

    private final GetCurrentUserPersonalCodeService getUser;

    private final JpaBankAccountRepository jpa;

    private final IbanMapper ibanMapper;


    public List<IbanDTO> getIbanDTO() {
        String personalCode = getUser.getCurrentUserPersonalCode();

        List<IBAN> ibanList = jpa.findByPersonalCode(personalCode).stream()
                .flatMap(bankAccount -> bankAccount.getIBAN().stream())
                .toList();
        return ibanList.stream()
                .map(ibanMapper::toDto).collect(Collectors.toList());
    }

    public List<String> getIbanNumber() {
        List<IBAN> ibanList = getIBAN();

        return ibanList.stream()
                .map(IBAN::getIbanNumber)
                .collect(Collectors.toList());

    }

    public List<IBAN> getIBAN() {
        String personalCode = getUser.getCurrentUserPersonalCode();

        return jpa.findByPersonalCode(personalCode).stream()
                .flatMap(bankAccount -> bankAccount.getIBAN().stream())
                .toList();
    }

}