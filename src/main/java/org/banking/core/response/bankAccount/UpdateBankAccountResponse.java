package org.banking.core.response.bankAccount;

import org.banking.core.response.CoreError;
import org.banking.core.response.CoreResponse;

import java.util.List;


public class UpdateBankAccountResponse extends CoreResponse {

    public UpdateBankAccountResponse(List<CoreError> errorList) {
        super(errorList);
    }

}
