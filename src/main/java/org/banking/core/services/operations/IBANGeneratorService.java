package org.banking.core.services.operations;

import org.banking.core.domain.BankAccount;
import org.banking.core.domain.IBAN;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class IBANGeneratorService {

    private static final Logger logger = LoggerFactory.getLogger(IBANGeneratorService.class);
    //TODO WRITE LOGIC FOR IT
    public List<IBAN> generateIBAN(BankAccount bankAccount) {
        logger.info("Making IBAN for BankAccount: {}", bankAccount.getPersonalCode());

        String ibanNumber = "LV" + "-" + UUID.randomUUID().toString().substring(0, 20).toUpperCase();

        IBAN iban = IBAN.builder()
                .ibanNumber(ibanNumber)
                .bankAccount(bankAccount)  // Устанавливаем связь
                .build();

        return List.of(iban);
    }


}
