package org.banking.core.services.validators;

import org.banking.core.request.DepositRequest;
import org.banking.core.request.WithdrawRequest;
import org.banking.core.response.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DepositValidator {

    public List<CoreError> validate(DepositRequest request) {
        List<CoreError> errorList = new ArrayList<>();

        validatePersonalCode(request).ifPresent(errorList::add);
        validateAmountOfWithdraw(request).ifPresent(errorList::add);

        return errorList;

    }

    private Optional<CoreError> validatePersonalCode(DepositRequest request) {
        return request.getPersonalCode() != null && !request.getPersonalCode().isBlank()
                ?Optional.empty()
                :Optional.of(new CoreError("Personal code field must be filled."));
    }

    private Optional<CoreError> validateAmountOfWithdraw(DepositRequest request) {
        return request.getAmount() > 0
                ?Optional.empty()
                :Optional.of(new CoreError("Amount must be positive."));
    }

}
