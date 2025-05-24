package org.banking.core.services.bankAccount;

import lombok.RequiredArgsConstructor;
import org.banking.core.database.JpaBankAccountRepository;
import org.banking.core.domain.BankAccount;
import org.banking.core.request.bankAccount.SearchBankAccountRequest;
import org.banking.core.response.CoreError;
import org.banking.core.response.bankAccount.SearchBankAccountResponse;
import org.banking.core.services.validators.bankAccountValidators.SearchBankAccountValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchBankAccountService {


    private final JpaBankAccountRepository bankRepository;
    private final SearchBankAccountValidator validator;

    private static final Logger logger = LoggerFactory.getLogger(SearchBankAccountService.class);

    public SearchBankAccountResponse execute(SearchBankAccountRequest request) {
        logger.debug("Validating request {}", request);
        List<CoreError> errors = validator.validate(request);

        if (!errors.isEmpty()) {
            logger.warn("Error while validating request: {},", request);
            return new SearchBankAccountResponse(errors, null);

        } else {
            logger.info("Searching Bank Account: {}", request);
            List<BankAccount> bankAccounts = search(request);

            logger.info("Successful response of searching:");
            return new SearchBankAccountResponse(null, bankAccounts);
        }
    }

    private List<BankAccount> search(SearchBankAccountRequest request) {
        List<BankAccount> bankAccounts = new ArrayList<>();

        if (request.nameNullCheck() && !request.surnameNullCheck() && !request.personalCodeNullCheck()) {
            logger.info("Searching Bank Account through Name: {}", request);
            bankAccounts = bankRepository.findByName(request.getName());
        }
        if (!request.nameNullCheck() && request.surnameNullCheck() && !request.personalCodeNullCheck()) {
            logger.info("Searching Bank Account through Surname: {}", request);
            bankAccounts = bankRepository.findBySurname(request.getSurname());
        }
        if (!request.nameNullCheck() && !request.surnameNullCheck() && request.personalCodeNullCheck()) {
            logger.info("Searching Bank Account through Personal Code: {}", request);
            bankAccounts = bankRepository.findByPersonalCode(request.getPersonalCode());
        }
        if (request.nameNullCheck() && request.surnameNullCheck() && !request.personalCodeNullCheck()) {
            logger.info("Searching Bank Account through Name and Surname: {}", request);
            bankAccounts = bankRepository.findByNameAndSurname(request.getName(), request.getSurname());
        }
        if (request.nameNullCheck() && !request.surnameNullCheck() && request.personalCodeNullCheck()) {
            logger.info("Searching Bank Account through Name and Personal Code: {}", request);
            bankAccounts = bankRepository.findByNameAndPersonalCode(request.getName(), request.getPersonalCode());
        }
        if (!request.nameNullCheck() && request.surnameNullCheck() && request.personalCodeNullCheck()) {
            logger.info("Searching Bank Account through Surname and Personal Code: {}", request);
            bankAccounts = bankRepository.findBySurnameAndPersonalCode(request.getSurname(), request.getPersonalCode());
        }
        if (request.nameNullCheck() && request.surnameNullCheck() && request.personalCodeNullCheck()) {
            logger.info("Searching Bank Account through Name, Surname and Personal Code: {}", request);
            bankAccounts = bankRepository.findByNameAndSurnameAndPersonalCode(request.getName(), request.getSurname(),
                    request.getPersonalCode());
        }
        logger.info("Returning response of the searching of this request: {}",request);
        return bankAccounts;
    }

 }