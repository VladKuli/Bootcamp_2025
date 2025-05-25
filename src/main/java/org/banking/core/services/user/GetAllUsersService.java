package org.banking.core.services.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.banking.core.database.JpaUserRepository;
import org.banking.core.domain.User;
import org.banking.core.dto.user.UserDTO;
import org.banking.core.mapper.user.UserMapper;
import org.banking.core.request.user.GetAllUsersRequest;
import org.banking.core.response.user.GetAllUsersResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetAllUsersService {

    private final JpaUserRepository userRepository;
    private final UserMapper userMapper;

    public GetAllUsersResponse execute(GetAllUsersRequest request) {
        log.info("Received request to retrieve all users");

        log.debug("Fetching all users from the database");
        List<User> users = userRepository.findAll();

        log.info("Successfully retrieved {} users from the database", users.size());

        log.debug("Masking sensitive information for users");
        List<User> safeUsers = maskingPassword(users);
        List<UserDTO> userDTOS = new ArrayList<>();

        for (User safeUser : safeUsers) {
            userDTOS.add(userMapper.toDto(safeUser));
        }


        log.info("Returning {} users with masked information", safeUsers.size());
        return new GetAllUsersResponse(userDTOS);
    }

    private List<User> maskingPassword(List<User> users) {
        return users.stream()
                .map(user -> User.builder()
                        .id(user.getId())
                        .personalCode(user.getPersonalCode())
                        .password("[ENCRYPTED]")
                        .role(user.getRole())
                        .build()
                )
                .collect(Collectors.toList());
    }

}
