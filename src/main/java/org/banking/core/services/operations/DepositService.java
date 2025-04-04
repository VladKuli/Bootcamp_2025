package org.banking.core.services.operations;

import org.banking.core.database.JpaBankAccountRepository;
import org.banking.core.domain.BankAccount;
import org.banking.core.domain.IBAN;
import org.banking.core.dto.iban.IbanDTO;
import org.banking.core.mapper.iban.IbanMapper;
import org.banking.core.request.operations.DepositRequest;
import org.banking.core.response.CoreError;
import org.banking.core.response.operations.DepositResponse;
import org.banking.core.services.bankAccount.GetCurrentBankAccountService;
import org.banking.core.services.iban.CurrentUserIbanService;
import org.banking.core.services.validators.operationsValidators.DepositValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepositService {

    @Autowired
    private JpaBankAccountRepository bankAccountRepository;

    @Autowired
    private DepositValidator validator;

    @Autowired
    private CurrentUserIbanService ibanService;


    private static final Logger logger = LoggerFactory.getLogger(DepositService.class);

    public DepositResponse execute(DepositRequest request) {
        logger.info("Received deposit request with amount: {}", request.getAmount());

        logger.debug("Validating deposit request: {}", request);
        List<CoreError> errorList = validator.validate(request);

        if (errorList.isEmpty()) {
            logger.info("Validation successful for deposit request: {}", request);

            logger.debug("Fetching personal code for the current user");


            List<IBAN> ibanList = ibanService.getIBAN();

            bankAccountRepository.ibanDeposit(ibanList.get(0).getId(), request.getAmount());


            return new DepositResponse(true);
        } else {

            logger.warn("Validation failed for deposit request: {}. Errors: {}", request, errorList);
            return new DepositResponse(errorList);
        }
    }

    public List<IbanDTO> getUsersIbanDTO() {
        return ibanService.getIbanDTO();
    }

}
