package org.banking.core.services.operations;

import org.banking.core.database.JpaBankAccountRepository;
import org.banking.core.request.operations.DepositRequest;
import org.banking.core.response.CoreError;
import org.banking.core.response.operations.DepositResponse;
import org.banking.core.services.user.GetCurrentUserPersonalCodeService;
import org.banking.core.services.validators.operationsValidators.DepositValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepositService {

    @Autowired
    private JpaBankAccountRepository bankAccountRepository;

    @Autowired
    private DepositValidator validator;

    @Autowired
    private GetCurrentUserPersonalCodeService personalCodeService;

    private static final Logger logger = LoggerFactory.getLogger(DepositService.class);

    public DepositResponse execute(DepositRequest request) {
        logger.info("Received deposit request with amount: {}", request.getAmount());

        logger.debug("Validating deposit request: {}", request);
        List<CoreError> errorList = validator.validate(request);

        if (errorList.isEmpty()) {
            logger.info("Validation successful for deposit request: {}", request);

            logger.debug("Fetching personal code for the current user");
            String personalCode = personalCodeService.getCurrentUserPersonalCode();
            logger.info("Personal code retrieved: {}", personalCode);

            logger.info("Depositing amount {} for personal code {}", request.getAmount(), personalCode);
            bankAccountRepository.deposit(personalCode, request.getAmount());

            logger.info("Deposit successful for personal code: {}", personalCode);
            return new DepositResponse(true);
        } else {

            logger.warn("Validation failed for deposit request: {}. Errors: {}", request, errorList);
            return new DepositResponse(errorList);
        }
    }
}
