package org.banking.core.services;

import org.banking.core.database.JpaUserRepository;
import org.banking.core.domain.User;
import org.banking.core.request.AddUserRequest;
import org.banking.core.response.AddUserResponse;
import org.banking.core.response.CoreError;
import org.banking.core.services.validators.AddUserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public class AddUserService {

    @Autowired
    private JpaUserRepository userRepository;

    @Autowired
    private AddUserValidator validator;

    public AddUserResponse execute(AddUserRequest request) {
        List<CoreError> errorList = validator.validate(request);

        if (errorList.isEmpty()) {
             User user = new User(request.getPersonalCode(),request.getPassword(),request.getRole());
             userRepository.save(user);

             return new AddUserResponse(user);
        } else {
            return new AddUserResponse(errorList);
        }
    }
}
