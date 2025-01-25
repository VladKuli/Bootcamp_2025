package org.banking.core.services;

import org.banking.core.database.JpaUserRepository;
import org.banking.core.domain.User;
import org.banking.core.request.AddUserRequest;
import org.banking.core.response.AddUserResponse;
import org.banking.core.response.CoreError;
import org.banking.core.services.validators.AddUserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddUserService {

    @Autowired
    private JpaUserRepository userRepository;

    @Autowired
    private AddUserValidator validator;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AddUserResponse execute(AddUserRequest request) {

        List<CoreError> errorList = validator.validate(request);

        if (errorList.isEmpty()) {

            String encryptedPassword = passwordEncoder.encode(request.getPassword());

            User user = new User(request.getPersonalCode(), encryptedPassword, request.getRole());
            userRepository.save(user);

            return new AddUserResponse(user);
        } else {
            return new AddUserResponse(errorList);
        }
    }
}
