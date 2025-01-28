package org.banking.core.services.operations;

import org.banking.core.database.JpaBankAccountRepository;
import org.banking.core.request.operations.WithdrawRequest;
import org.banking.core.response.CoreError;
import org.banking.core.response.operations.WithdrawResponse;
import org.banking.core.services.user.GetCurrentUserPersonalCodeService;
import org.banking.core.services.validators.operationsValidators.WithdrawValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class WithdrawService {

    @Autowired
    private JpaBankAccountRepository bankAccountRepository;

    @Autowired
    private GetCurrentUserPersonalCodeService personalCodeService;

    @Autowired
    private WithdrawValidator validator;

    private static final Logger logger = LoggerFactory.getLogger(WithdrawService.class);

    public WithdrawResponse execute(WithdrawRequest request) {
        logger.info("Received withdraw request with amount: {}", request.getAmount());

        logger.debug("Validating withdraw request: {}", request);
        List<CoreError> errorList = validator.validate(request);

        if (errorList.isEmpty()) {
            logger.info("Validation successful for withdraw request: {}", request);

            logger.debug("Fetching personal code for the current user");
            String personalCode = personalCodeService.getCurrentUserPersonalCode();
            logger.info("Personal code retrieved: {}", personalCode);

            logger.info("Processing withdrawal of amount {} for personal code {}", request.getAmount(), personalCode);
            bankAccountRepository.withdraw(personalCode, request.getAmount());
            logger.info("Withdrawal successful for personal code: {}. Amount: {}", personalCode, request.getAmount());

            return new WithdrawResponse(true);
        } else {
            logger.warn("Validation failed for withdraw request: {}. Errors: {}", request, errorList);
            return new WithdrawResponse(errorList);
        }
    }
}
