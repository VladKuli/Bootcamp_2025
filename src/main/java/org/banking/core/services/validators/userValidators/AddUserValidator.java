package org.banking.core.services.validators.userValidators;

import org.banking.core.request.user.AddUserRequest;
import org.banking.core.response.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AddUserValidator {

    public List<CoreError> validate(AddUserRequest request) {
        List<CoreError> errorList = new ArrayList<>();

        validatePersonalCode(request).ifPresent(errorList::add);
        validatePassword(request).ifPresent(errorList::add);
        validateRole(request).ifPresent(errorList::add);

        return errorList;
    }

    private Optional<CoreError> validatePersonalCode(AddUserRequest request) {
        return request.getPersonalCode() == null || request.getPersonalCode().isBlank()
                ? Optional.of(new CoreError("Personal code field must be filled"))
                : Optional.empty();
    }

    private Optional<CoreError> validatePassword(AddUserRequest request) {
        return request.getPassword() == null || request.getPassword().isBlank()
                ? Optional.of(new CoreError("Password field must be filled"))
                : Optional.empty();
    }

    private Optional<CoreError> validateRole(AddUserRequest request) {
        return request.getRole() == null || request.getRole().isBlank()
                ? Optional.of(new CoreError("Role field must be filled"))
                : Optional.empty();
    }
}
