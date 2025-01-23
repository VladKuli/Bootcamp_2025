package org.banking.core.response;

import lombok.Getter;

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
