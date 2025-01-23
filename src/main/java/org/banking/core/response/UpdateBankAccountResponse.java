package org.banking.core.response;

import java.util.List;


public class UpdateBankAccountResponse extends CoreResponse {

    public UpdateBankAccountResponse(List<CoreError> errorList) {
        super(errorList);
    }

}
