package org.banking.core.services.operations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class IBANGeneratorService {

    private static final Logger logger = LoggerFactory.getLogger(IBANGeneratorService.class);

    public  String generateIBAN(String personalCode) {
        logger.info("Making IBAN for BankAccount");
        return "LV" + "-" + UUID.randomUUID().toString().substring(0, 20).toUpperCase();
    }

}
