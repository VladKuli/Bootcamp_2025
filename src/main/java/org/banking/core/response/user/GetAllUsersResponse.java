package org.banking.core.response.user;

import lombok.Getter;
import org.banking.core.domain.User;
import org.banking.core.dto.user.UserDTO;

import java.util.List;


@Getter
public class GetAllUsersResponse{

    private List<UserDTO> userDTOS;

    public GetAllUsersResponse(List<UserDTO> userDTOS) {
        this.userDTOS = userDTOS;
    }

}
