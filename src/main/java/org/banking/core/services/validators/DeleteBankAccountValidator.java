package org.banking.core.services.validators;

import org.banking.core.request.DeleteBankAccountRequest;
import org.banking.core.response.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DeleteBankAccountValidator {


    public List<CoreError> validate(DeleteBankAccountRequest request) {
        List<CoreError> errorList = new ArrayList<>();

        validatePersonalCode(request).ifPresent(errorList::add);

        return errorList;

    }

    private Optional<CoreError> validatePersonalCode(DeleteBankAccountRequest request) {
        return request.getPersonalCode() != null && !request.getPersonalCode().isBlank()
                ?Optional.empty()
                :Optional.of(new CoreError("Personal code field must be filled"));
    }
}
