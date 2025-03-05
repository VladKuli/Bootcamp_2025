package org.banking.core.response.bankAccount;

import org.banking.core.domain.BankAccount;
import org.banking.core.response.CoreError;
import org.banking.core.response.CoreResponse;

import java.util.List;

public class GetCurrentBankAccountResponse extends CoreResponse {

    private BankAccount bankAccount;

    public GetCurrentBankAccountResponse(List<CoreError> errorList) {
        super(errorList);
    }

    public GetCurrentBankAccountResponse(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }
}
