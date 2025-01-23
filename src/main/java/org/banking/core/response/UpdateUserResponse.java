package org.banking.core.response;

import java.util.List;

public class UpdateUserResponse extends CoreResponse {

    public UpdateUserResponse(List<CoreError> errorList) {
        super(errorList);
    }
}
