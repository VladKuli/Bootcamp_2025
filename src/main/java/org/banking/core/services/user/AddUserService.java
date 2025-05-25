package org.banking.core.services.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.banking.core.database.JpaUserRepository;
import org.banking.core.domain.User;
import org.banking.core.request.user.AddUserRequest;
import org.banking.core.response.user.AddUserResponse;
import org.banking.core.response.CoreError;
import org.banking.core.services.validators.userValidators.AddUserValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Slf4j
@Service
@RequiredArgsConstructor
public class AddUserService {

    private final JpaUserRepository userRepository;

    private final AddUserValidator validator;

    private final PasswordEncoder passwordEncoder;


    public AddUserResponse execute(AddUserRequest request) {
        log.info("Received request to add a new user with personal code: {}", request.getPersonalCode());

        log.debug("Validating add user request: {}", request);
        List<CoreError> errorList = validator.validate(request);

        if (errorList.isEmpty()) {
            log.info("Validation successful for add user request: {}", request);

            Optional<User> user = userBuilding(request);
            if (user.isPresent()) {
                log.info("Saving user with personal code: {}", user.get().getPersonalCode());
                userRepository.save(user.get());

                log.info("User successfully added with personal code: {}", user.get().getPersonalCode());
                return new AddUserResponse(user.get());
            }
        }
        log.warn("Validation failed for add user request: {}. Errors: {}", request, errorList);
        return new AddUserResponse(errorList);
    }

    private Optional<User> userBuilding(AddUserRequest request) {

        log.debug("Encrypting password for user with personal code: {}", request.getPersonalCode());
        String encryptedPassword = passwordEncoder.encode(request.getPassword());

        log.debug("Building user entity for personal code: {}", request.getPersonalCode());
        User user = User.builder()
                .personalCode(request.getPersonalCode())
                .password(encryptedPassword)
                .role(request.getRole())
                .build();

        return Optional.of(user);
    }
}