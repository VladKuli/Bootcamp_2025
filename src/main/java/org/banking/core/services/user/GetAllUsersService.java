package org.banking.core.services.user;

import org.banking.core.database.JpaUserRepository;
import org.banking.core.domain.User;
import org.banking.core.request.user.GetAllUsersRequest;
import org.banking.core.response.user.GetAllUsersResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetAllUsersService {

    @Autowired
    private JpaUserRepository userRepository;

    public GetAllUsersResponse execute(GetAllUsersRequest request) {
        List<User> users = userRepository.findAll();

        List<User> safeUsers = users.stream()
                .map(user -> User.builder()
                        .id(user.getId())
                        .personalCode(user.getPersonalCode())
                        .password("[ENCRYPTED]")
                        .role(user.getRole())
                        .build()
                )
                .collect(Collectors.toList());

        return new GetAllUsersResponse(safeUsers);
    }
}
