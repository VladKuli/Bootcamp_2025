package org.banking.core.services.operations;

import lombok.extern.slf4j.Slf4j;
import org.banking.core.domain.BankAccount;
import org.banking.core.domain.IBAN;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Slf4j
@Service
public class IBANGeneratorService {

    public List<IBAN> generateIBAN(BankAccount bankAccount) {
        log.info("Making IBAN for BankAccount: {}", bankAccount.getPersonalCode());

        String ibanNumber = "LV" + "-" + UUID.randomUUID().toString().substring(0, 20).toUpperCase();

        IBAN iban = IBAN.builder()
                .ibanNumber(ibanNumber)
                .bankAccount(bankAccount)
                .build();

        return List.of(iban);
    }



}
