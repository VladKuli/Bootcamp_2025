package org.banking.core.services;

import org.banking.core.database.JpaUserRepository;
import org.banking.core.domain.User;
import org.banking.core.request.GetAllUsersRequest;
import org.banking.core.response.GetAllUsersResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAllUsersService {


    @Autowired
    private JpaUserRepository userRepository;

    public GetAllUsersResponse execute(GetAllUsersRequest request) {
        List<User> users = userRepository.findAll();
        return new GetAllUsersResponse(users);
    }
}
