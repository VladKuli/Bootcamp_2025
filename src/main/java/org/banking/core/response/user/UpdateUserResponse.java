package org.banking.core.response.user;

import org.banking.core.response.CoreError;
import org.banking.core.response.CoreResponse;

import java.util.List;

public class UpdateUserResponse extends CoreResponse {

    public UpdateUserResponse(List<CoreError> errorList) {
        super(errorList);
    }
}
