package org.banking.core.services.operations;

import org.banking.core.database.JpaBankAccountRepository;
import org.banking.core.domain.BankAccount;
import org.banking.core.domain.Card;
import org.banking.core.request.operations.DepositRequest;
import org.banking.core.response.CoreError;
import org.banking.core.response.operations.DepositResponse;
import org.banking.core.services.bankAccount.GetCurrentBankAccountService;
import org.banking.core.services.user.GetCurrentUserPersonalCodeService;
import org.banking.core.services.validators.operationsValidators.DepositValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private GetCurrentBankAccountService getCurrentBankAccountService;

    private static final Logger logger = LoggerFactory.getLogger(DepositService.class);

    public DepositResponse execute(DepositRequest request) {
        logger.info("Received deposit request with amount: {}", request.getAmount());

        logger.debug("Validating deposit request: {}", request);
        List<CoreError> errorList = validator.validate(request);

        if (errorList.isEmpty()) {
            logger.info("Validation successful for deposit request: {}", request);

            logger.debug("Fetching personal code for the current user");
            String personalCode = getCurrentBankAccountService
                    .get()
                    .map(BankAccount::getPersonalCode)
                    .orElseThrow(() -> new RuntimeException("Personal code not exist"));


            logger.info("Depositing amount {} for personal code {}", request.getAmount(), personalCode);
            bankAccountRepository.deposit(personalCode, request.getAmount());

            bankAccountRepository.cardDeposit(request.getCardNumber(), request.getAmount());
            logger.info("Deposit successful for personal code: {}", personalCode);
            return new DepositResponse(true);
        } else {

            logger.warn("Validation failed for deposit request: {}. Errors: {}", request, errorList);
            return new DepositResponse(errorList);
        }
    }

    public List<Card> getUsersCards() {
        return getCurrentBankAccountService.get()
                .stream()
                .flatMap(bankAccount -> bankAccount.getCards().stream())
                .collect(Collectors.toList());
    }
}
