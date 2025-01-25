package org.banking.core.response.user;

import lombok.Getter;
import org.banking.core.domain.User;
import org.banking.core.response.CoreError;
import org.banking.core.response.CoreResponse;

import java.util.List;

@Getter
public class AddUserResponse extends CoreResponse {

    private User user;

    public AddUserResponse(List<CoreError> errorList) {
        super(errorList);
    }

    public AddUserResponse(User user) {
        this.user = user;
    }

}
