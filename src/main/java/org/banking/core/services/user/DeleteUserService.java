package org.banking.core.services.user;

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

@Service
@Transactional
public class DeleteUserService {

    @Autowired
    private JpaBankAccountRepository bankAccountRepository;

    @Autowired
    private JpaUserRepository userRepository;

    @Autowired
    private DeleteUserValidator validator;

    private static final Logger logger = LoggerFactory.getLogger(DeleteUserService.class);

    public DeleteUserResponse execute(DeleteUserRequest request) {
        logger.info("Received request to delete user with personal code: {}", request.getPersonalCode());

        logger.debug("Validating delete user request: {}", request);
        List<CoreError> errorList = validator.validate(request);

        if (errorList.isEmpty()) {
            logger.info("Validation successful for delete user request: {}", request);

            logger.info("Deleting user with personal code: {}", request.getPersonalCode());
            userRepository.deleteByPersonalCode(request.getPersonalCode());
            logger.info("User with personal code {} successfully deleted", request.getPersonalCode());

            logger.info("Deleting associated bank accounts for personal code: {}", request.getPersonalCode());
            bankAccountRepository.deleteByPersonalCode(request.getPersonalCode());
            logger.info("Associated bank accounts for personal code {} successfully deleted", request.getPersonalCode());

            logger.info("Delete user operation completed successfully for personal code: {}", request.getPersonalCode());
            return new DeleteUserResponse(true);
        } else {
            logger.warn("Validation failed for delete user request: {}. Errors: {}", request, errorList);
            return new DeleteUserResponse(errorList);
        }
    }
}
