package org.banking.core.services.operations;

import org.banking.core.database.JpaBankAccountRepository;
import org.banking.core.request.operations.MoneyTransferRequest;
import org.banking.core.response.CoreError;
import org.banking.core.response.operations.MoneyTransferResponse;
import org.banking.core.services.user.GetCurrentUserPersonalCodeService;
import org.banking.core.services.validators.operationsValidators.MoneyTransferValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MoneyTransferService {

    @Autowired
    private JpaBankAccountRepository bankAccountRepository;

    @Autowired
    private GetCurrentUserPersonalCodeService personalCodeService;

    @Autowired
    private MoneyTransferValidator validator;

    private static final Logger logger = LoggerFactory.getLogger(MoneyTransferService.class);

    public MoneyTransferResponse execute(MoneyTransferRequest request) {
        logger.info("Received money transfer request from current user to target personal code: {} with amount: {}",
                request.getTargetPersonalCode(), request.getAmount());

        String senderPersonalCode = personalCodeService.getCurrentUserPersonalCode();

        logger.debug("Validating money transfer request: {}", request);
        List<CoreError> errorList = validator.execute(request);

        if (errorList.isEmpty()) {
            logger.info("Validation successful for money transfer request: {}", request);

            logger.debug("Retrieved sender personal code: {}", senderPersonalCode);

            logger.info("Initiating money transfer from {} to {} with amount: {}",
                    senderPersonalCode, request.getTargetPersonalCode(), request.getAmount());

            bankAccountRepository.bankTransfer(senderPersonalCode,
                    request.getTargetPersonalCode(), request.getAmount());

            logger.info("Money transfer successful from {} to {} with amount: {}",
                    senderPersonalCode, request.getTargetPersonalCode(), request.getAmount());

            return new MoneyTransferResponse(true);
        } else {
            logger.warn("Validation failed for money transfer request: {}. Errors: {}", request, errorList);
            return new MoneyTransferResponse(errorList);
        }
    }
}