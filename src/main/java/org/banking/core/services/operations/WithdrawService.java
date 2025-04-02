package org.banking.core.services.operations;

import io.swagger.v3.oas.models.info.License;
import org.banking.core.database.JpaBankAccountRepository;
import org.banking.core.domain.BankAccount;
import org.banking.core.domain.Card;
import org.banking.core.domain.IBAN;
import org.banking.core.request.operations.WithdrawRequest;
import org.banking.core.response.CoreError;
import org.banking.core.response.operations.WithdrawResponse;
import org.banking.core.services.bankAccount.GetCurrentBankAccountService;
import org.banking.core.services.user.GetCurrentUserPersonalCodeService;
import org.banking.core.services.validators.operationsValidators.WithdrawValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class WithdrawService {

    @Autowired
    private JpaBankAccountRepository bankAccountRepository;

    @Autowired
    private GetCurrentBankAccountService getCurrentBankAccount;

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

            String personalCode = getCurrentBankAccount.get()
                    .map(BankAccount::getPersonalCode)
                    .orElseThrow(() -> new RuntimeException("Personal code not found"));
            logger.info("Personal code retrieved: {}", personalCode);

            bankAccountRepository.deductBalanceForIban(request.getAmount(),request.getIBAN());
            logger.info("Processing withdrawal of amount {} for personal code {}", request.getAmount(), personalCode);
            logger.info("Withdrawal successful for personal code: {}. Amount: {}", personalCode, request.getAmount());

            return new WithdrawResponse(true);
        } else {
            logger.warn("Validation failed for withdraw request: {}. Errors: {}", request, errorList);
            return new WithdrawResponse(errorList);
        }
    }

    public List<IBAN> getUsersCards() {
        return getCurrentBankAccount.getIBAN();
    }
}
