package org.banking.core.services.validators.operationsValidators;

import org.banking.core.request.operations.WithdrawRequest;
import org.banking.core.response.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class WithdrawValidator {


    public List<CoreError> validate(WithdrawRequest request) {
        List<CoreError> errorList = new ArrayList<>();

        validateAmountOfWithdraw(request).ifPresent(errorList::add);

        return errorList;

    }

    private Optional<CoreError> validateAmountOfWithdraw(WithdrawRequest request) {
        return request.getAmount() > 0
                ?Optional.empty()
                :Optional.of(new CoreError("Amount must be positive."));
    }
}
