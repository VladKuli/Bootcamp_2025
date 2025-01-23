package org.banking.core.response;

import lombok.Getter;

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
