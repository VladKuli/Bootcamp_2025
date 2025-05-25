package org.banking.core.services.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.banking.core.database.JpaBankAccountRepository;
import org.banking.core.database.JpaUserRepository;
import org.banking.core.request.user.DeleteUserRequest;
import org.banking.core.response.CoreError;
import org.banking.core.response.user.DeleteUserResponse;
import org.banking.core.services.validators.userValidators.DeleteUserValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class DeleteUserService {

    private final JpaBankAccountRepository bankAccountRepository;

    private final JpaUserRepository userRepository;

    private final DeleteUserValidator validator;


    public DeleteUserResponse execute(DeleteUserRequest request) {
        log.info("Received request to delete user with personal code: {}", request.getPersonalCode());

        log.debug("Validating delete user request: {}", request);
        List<CoreError> errorList = validator.validate(request);

        if (errorList.isEmpty()) {
            log.info("Validation successful for delete user request: {}", request);

            log.info("Deleting user with personal code: {}", request.getPersonalCode());
            userRepository.deleteByPersonalCode(request.getPersonalCode());
            log.info("User with personal code {} successfully deleted", request.getPersonalCode());

            log.info("Deleting associated bank accounts for personal code: {}", request.getPersonalCode());
            bankAccountRepository.deleteByPersonalCode(request.getPersonalCode());
            log.info("Associated bank accounts for personal code {} successfully deleted", request.getPersonalCode());

            log.info("Delete user operation completed successfully for personal code: {}", request.getPersonalCode());
            return new DeleteUserResponse(true);
        } else {
            log.warn("Validation failed for delete user request: {}. Errors: {}", request, errorList);
            return new DeleteUserResponse(errorList);
        }
    }
}
