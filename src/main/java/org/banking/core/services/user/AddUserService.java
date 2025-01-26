package org.banking.core.services.user;

import org.banking.core.database.JpaUserRepository;
import org.banking.core.domain.User;
import org.banking.core.request.user.AddUserRequest;
import org.banking.core.response.user.AddUserResponse;
import org.banking.core.response.CoreError;
import org.banking.core.services.validators.userValidators.AddUserValidator;
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

    public AddUserResponse execute(AddUserRequest request) {

        List<CoreError> errorList = validator.validate(request);

        if (errorList.isEmpty()) {

            String encryptedPassword = passwordEncoder.encode(request.getPassword());

            User user = User.builder()
                    .personalCode(request.getPersonalCode())
                    .password(encryptedPassword)
                    .role(request.getRole())
                    .build();

            userRepository.save(user);

            return new AddUserResponse(user);
        } else {
            return new AddUserResponse(errorList);
        }
    }
}
