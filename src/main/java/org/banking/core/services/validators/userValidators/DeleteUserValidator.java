package org.banking.core.services.validators.userValidators;


import org.banking.core.request.user.DeleteUserRequest;
import org.banking.core.response.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DeleteUserValidator {



    public List<CoreError> validate(DeleteUserRequest request) {
        List<CoreError> errorList = new ArrayList<>();

        validatePersonalCode(request).ifPresent(errorList::add);

        return errorList;
    }

    private Optional<CoreError> validatePersonalCode(DeleteUserRequest request) {
        return request.getPersonalCode() != null && !request.getPersonalCode().isBlank()
                ?Optional.empty()
                :Optional.of(new CoreError("Personal code field must be filled"));
    }
}
