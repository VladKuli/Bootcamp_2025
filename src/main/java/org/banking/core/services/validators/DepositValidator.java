package org.banking.core.services.validators;

import org.banking.core.request.operations.DepositRequest;
import org.banking.core.response.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DepositValidator {

    public List<CoreError> validate(DepositRequest request) {
        List<CoreError> errorList = new ArrayList<>();
        validateAmountOfWithdraw(request).ifPresent(errorList::add);

        return errorList;

    }

    private Optional<CoreError> validateAmountOfWithdraw(DepositRequest request) {
        return request.getAmount() > 0
                ?Optional.empty()
                :Optional.of(new CoreError("Amount must be positive."));
    }

}
