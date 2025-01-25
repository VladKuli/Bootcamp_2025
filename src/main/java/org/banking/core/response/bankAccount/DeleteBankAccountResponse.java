package org.banking.core.response.bankAccount;

import lombok.Getter;
import org.banking.core.response.CoreError;
import org.banking.core.response.CoreResponse;

import java.util.List;

@Getter
public class DeleteBankAccountResponse extends CoreResponse {

    private boolean isDeleted;

    public DeleteBankAccountResponse(List<CoreError> errorList) {
        super(errorList);
    }

    public DeleteBankAccountResponse(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}
