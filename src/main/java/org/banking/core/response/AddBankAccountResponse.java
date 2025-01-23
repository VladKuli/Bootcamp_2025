package org.banking.core.response;

import lombok.Getter;
import org.banking.core.domain.BankAccount;

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
