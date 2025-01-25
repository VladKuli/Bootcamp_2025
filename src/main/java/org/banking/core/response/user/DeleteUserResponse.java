package org.banking.core.response.user;

import lombok.Getter;
import org.banking.core.response.CoreError;
import org.banking.core.response.CoreResponse;

import java.util.List;

@Getter
public class DeleteUserResponse extends CoreResponse {

    private boolean isDeleted;

    public DeleteUserResponse(List<CoreError> errorList) {
        super(errorList);
    }

    public DeleteUserResponse(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}
