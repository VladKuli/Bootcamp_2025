package org.banking.core.services.validators.bankAccountValidators;

import org.banking.core.request.bankAccount.SearchBankAccountRequest;
import org.banking.core.response.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class SearchBankAccountValidator {

    public List<CoreError> validate(SearchBankAccountRequest request) {
        List<CoreError> errors = new ArrayList<>();
        if (isEmpty(request.getName()) && isEmpty(request.getSurname()) && isEmpty(request.getPersonalCode())) {
            errors.add(new CoreError("Name may contains only letters " +
                    "and cannot be empty"));
            errors.add(new CoreError("Surname may contains only letters " +
                    "and cannot be empty"));
            errors.add(new CoreError("Personal code may contains only numbers " +
                    "and cannot be empty"));
        }
        return errors;
    }
    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }
}
