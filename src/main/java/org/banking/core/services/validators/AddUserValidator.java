package org.banking.core.services.validators;

import org.banking.core.database.JpaUserRepository;
import org.banking.core.request.AddUserRequest;
import org.banking.core.response.AddUserResponse;
import org.banking.core.response.CoreError;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AddUserValidator {


    public List<CoreError> validate (AddUserRequest request) {
        List<CoreError> errorList = new ArrayList<>();

        validatePersonalCode(request).ifPresent(errorList::add);
        validatePassword(request).ifPresent(errorList::add);
        validateRole(request).ifPresent(errorList::add);

        return errorList;
    }

    private Optional<CoreError> validatePersonalCode(AddUserRequest request) {
        return request.getPersonalCode() != null && !request.getPersonalCode().isBlank()
                ?Optional.empty()
                :Optional.of(new CoreError("Personal code field must be filled"));
    }

    private Optional<CoreError> validatePassword(AddUserRequest request) {
        return request.getPersonalCode() != null && !request.getPersonalCode().isBlank()
                ?Optional.empty()
                :Optional.of(new CoreError("Password field must be filled"));
    }

    private Optional<CoreError> validateRole(AddUserRequest request) {
        return request.getPersonalCode() != null && !request.getPersonalCode().isBlank()
                ?Optional.empty()
                :Optional.of(new CoreError("Role field must be filled"));
    }
}
