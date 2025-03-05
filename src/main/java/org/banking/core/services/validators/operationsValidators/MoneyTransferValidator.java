package org.banking.core.services.validators.operationsValidators;

import org.banking.core.request.operations.MoneyTransferRequest;
import org.banking.core.response.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Component
public class MoneyTransferValidator {

    public List<CoreError> execute(MoneyTransferRequest request) {
        List<CoreError> errorList = new ArrayList<>();
        validateTargetPersonalCode(request).ifPresent(errorList::add);
        validateAmount(request).ifPresent(errorList::add);

        return errorList;
    }

    private Optional<CoreError> validateTargetPersonalCode(MoneyTransferRequest request) {
        return !request.getTargetIBAN().isBlank()
                ? Optional.empty()
                :Optional.of(new CoreError("Personal code field must be filled."));
    }

    private Optional<CoreError> validateAmount(MoneyTransferRequest request) {
        return request.getAmount() > 0 && request.getTargetIBAN() != null
                ? Optional.empty()
                :Optional.of(new CoreError("Amount must be positive."));
    }
}
