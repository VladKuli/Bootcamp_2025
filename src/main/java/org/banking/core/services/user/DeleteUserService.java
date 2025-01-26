package org.banking.core.services.user;

import org.banking.core.database.JpaBankAccountRepository;
import org.banking.core.database.JpaUserRepository;
import org.banking.core.request.user.DeleteUserRequest;
import org.banking.core.response.CoreError;
import org.banking.core.response.user.DeleteUserResponse;
import org.banking.core.services.validators.userValidators.DeleteUserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DeleteUserService {

    @Autowired
    private JpaBankAccountRepository bankAccountRepository;
    @Autowired
    private JpaUserRepository userRepository;
    @Autowired
    private DeleteUserValidator validator;

    public DeleteUserResponse execute(DeleteUserRequest request) {
        List<CoreError> errorList = validator.validate(request);

        if (errorList.isEmpty()) {
            userRepository.deleteByPersonalCode(request.getPersonalCode());
            bankAccountRepository.deleteByPersonalCode(request.getPersonalCode());

            return new DeleteUserResponse(true);

        } else {
            return new DeleteUserResponse(errorList);
        }
    }
}
