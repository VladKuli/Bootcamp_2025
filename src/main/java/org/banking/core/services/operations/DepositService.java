package org.banking.core.services.operations;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.banking.core.database.JpaBankAccountRepository;
import org.banking.core.domain.IBAN;
import org.banking.core.dto.iban.IbanDTO;
import org.banking.core.request.operations.DepositRequest;
import org.banking.core.response.CoreError;
import org.banking.core.response.operations.DepositResponse;
import org.banking.core.services.iban.CurrentUserIbanService;
import org.banking.core.services.validators.operationsValidators.DepositValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DepositService {

    private final JpaBankAccountRepository bankAccountRepository;

    private final DepositValidator validator;

    private final CurrentUserIbanService ibanService;



    public DepositResponse execute(DepositRequest request) {
        log.info("Received deposit request with amount: {}", request.getAmount());

        log.debug("Validating deposit request: {}", request);
        List<CoreError> errorList = validator.validate(request);

        if (errorList.isEmpty()) {

            List<IBAN> ibanList = ibanService.getIBAN();

            bankAccountRepository.ibanDeposit(ibanList.get(0).getId(), request.getAmount());

            return new DepositResponse(true);
        } else {

            log.warn("Validation failed for deposit request: {}. Errors: {}", request, errorList);
            return new DepositResponse(errorList);
        }
    }

    public List<IbanDTO> getUsersIbanDTO() {
        return ibanService.getIbanDTO();
    }

}
