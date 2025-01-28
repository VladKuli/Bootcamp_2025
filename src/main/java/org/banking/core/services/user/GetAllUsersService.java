package org.banking.core.services.user;

import org.banking.core.database.JpaUserRepository;
import org.banking.core.domain.User;
import org.banking.core.request.user.GetAllUsersRequest;
import org.banking.core.response.user.GetAllUsersResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetAllUsersService {

    @Autowired
    private JpaUserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(GetAllUsersService.class);

    public GetAllUsersResponse execute(GetAllUsersRequest request) {
        logger.info("Received request to retrieve all users");

        logger.debug("Fetching all users from the database");
        List<User> users = userRepository.findAll();

        logger.info("Successfully retrieved {} users from the database", users.size());

        logger.debug("Masking sensitive information for users");
        List<User> safeUsers = users.stream()
                .map(user -> User.builder()
                        .id(user.getId())
                        .personalCode(user.getPersonalCode())
                        .password("[ENCRYPTED]")
                        .role(user.getRole())
                        .build()
                )
                .collect(Collectors.toList());

        logger.info("Returning {} users with masked information", safeUsers.size());
        return new GetAllUsersResponse(safeUsers);
    }
}
