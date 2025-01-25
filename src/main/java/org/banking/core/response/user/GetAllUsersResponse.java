package org.banking.core.response.user;

import lombok.Getter;
import org.banking.core.domain.User;

import java.util.List;


@Getter
public class GetAllUsersResponse{

    private List<User> users;

    public GetAllUsersResponse(List<User> users) {
        this.users = users;
    }
}
