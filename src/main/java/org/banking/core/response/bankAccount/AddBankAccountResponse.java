package org.banking.core.response.bankAccount;

import lombok.Getter;
import org.banking.core.domain.BankAccount;
import org.banking.core.response.CoreError;
import org.banking.core.response.CoreResponse;

import java.util.List;

@Getter
public class AddBankAccountResponse extends CoreResponse {

    private BankAccount bankAccount;

    public AddBankAccountResponse(List<CoreError> errorList) {
        super(errorList);
    }

    public AddBankAccountResponse(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }
}
