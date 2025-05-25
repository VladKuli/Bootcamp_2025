package org.banking.core.services.operations;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.banking.core.database.JpaBankAccountRepository;
import org.banking.core.dto.iban.IbanDTO;
import org.banking.core.request.operations.WithdrawRequest;
import org.banking.core.response.CoreError;
import org.banking.core.response.operations.WithdrawResponse;
import org.banking.core.services.iban.CurrentUserIbanService;
import org.banking.core.services.validators.operationsValidators.WithdrawValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class WithdrawService {

    private final JpaBankAccountRepository bankAccountRepository;

    private final WithdrawValidator validator;

    private final CurrentUserIbanService ibanService;

    public WithdrawResponse execute(WithdrawRequest request) {
        log.info("Received withdraw request with amount: {}", request.getAmount());

        log.debug("Validating withdraw request: {}", request);
        List<CoreError> errorList = validator.validate(request);

        if (errorList.isEmpty()) {
            log.info("Validation successful for withdraw request: {}", request);

            log.debug("Fetching personal code for the current user");

            bankAccountRepository.deductBalanceForIban(request.getAmount(),request.getIBAN());

            return new WithdrawResponse(true);
        } else {
            log.warn("Validation failed for withdraw request: {}. Errors: {}", request, errorList);
            return new WithdrawResponse(errorList);
        }
    }

    public List<IbanDTO> getUsersIBANSDTO() {
         return ibanService.getIbanDTO();
    }
}