package org.banking.core.response;

import org.banking.core.domain.User;

import java.util.List;


public class GetAllUsersResponse{

    private List<User> users;

    public GetAllUsersResponse(List<User> users) {
        this.users = users;
    }
}
