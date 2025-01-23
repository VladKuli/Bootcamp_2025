package org.banking.core.services.validators;

import org.banking.core.database.JpaBankAccountRepository;
import org.banking.core.domain.BankAccount;
import org.banking.core.request.AddBankAccountRequest;
import org.banking.core.response.AddBankAccountResponse;
import org.banking.core.response.CoreError;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AddBankAccountValidator {


    public List<CoreError> validate (AddBankAccountRequest request) {
        List<CoreError> errorList = new ArrayList<>();

        validateName(request).ifPresent(errorList::add);
        validateSurname(request).ifPresent(errorList::add);
        validatePersonalCode(request).ifPresent(errorList::add);

        return errorList;
    }

    private Optional<CoreError> validateName(AddBankAccountRequest request) {
        return request.getName() != null && !request.getName().isBlank()
                ?Optional.empty()
                :Optional.of(new CoreError("Name field must be filled"));
    }

    private  Optional<CoreError> validateSurname(AddBankAccountRequest request) {
        return request.getSurname() != null && !request.getSurname().isBlank()
                ?Optional.empty()
                :Optional.of(new CoreError("Surname field must be filled"));
    }

    private Optional<CoreError> validatePersonalCode(AddBankAccountRequest request) {
        return request.getPersonalCode() != null && !request.getPersonalCode().isBlank()
                ?Optional.empty()
                :Optional.of(new CoreError("Personal code field must be filled"));
    }

}
