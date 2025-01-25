package org.banking.core.response.operations;

import lombok.Getter;
import org.banking.core.domain.BankAccount;
import org.banking.core.response.CoreError;
import org.banking.core.response.CoreResponse;

import java.util.List;
import java.util.Optional;

@Getter
public class SeeYourBalanceResponse extends CoreResponse {

    private Optional<BankAccount> bankAccount;

    public SeeYourBalanceResponse(List<CoreError> errorList) {
        super(errorList);
    }

    public SeeYourBalanceResponse(Optional<BankAccount> bankAccount) {
        this.bankAccount = bankAccount;
    }
}
