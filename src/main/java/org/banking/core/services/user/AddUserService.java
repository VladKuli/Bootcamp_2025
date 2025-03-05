package org.banking.core.services.user;

import org.banking.core.database.JpaUserRepository;
import org.banking.core.domain.User;
import org.banking.core.request.user.AddUserRequest;
import org.banking.core.response.user.AddUserResponse;
import org.banking.core.response.CoreError;
import org.banking.core.services.validators.userValidators.AddUserValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddUserService {

    @Autowired
    private JpaUserRepository userRepository;

    @Autowired
    private AddUserValidator validator;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(AddUserService.class);

    public AddUserResponse execute(AddUserRequest request) {
        logger.info("Received request to add a new user with personal code: {}", request.getPersonalCode());

        logger.debug("Validating add user request: {}", request);
        List<CoreError> errorList = validator.validate(request);

        if (errorList.isEmpty()) {
            logger.info("Validation successful for add user request: {}", request);

            logger.debug("Encrypting password for user with personal code: {}", request.getPersonalCode());
            String encryptedPassword = passwordEncoder.encode(request.getPassword());

            logger.debug("Building user entity for personal code: {}", request.getPersonalCode());
            User user = User.builder()
                    .personalCode(request.getPersonalCode())
                    .password(encryptedPassword)
                    .role(request.getRole())
                    .build();

            logger.info("Saving user with personal code: {}", user.getPersonalCode());
            userRepository.save(user);

            logger.info("User successfully added with personal code: {}", user.getPersonalCode());
            return new AddUserResponse(user);
        } else {
            logger.warn("Validation failed for add user request: {}. Errors: {}", request, errorList);
            return new AddUserResponse(errorList);
        }
    }
}