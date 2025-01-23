package org.banking.core.response;

import lombok.Getter;
import org.banking.core.domain.BankAccount;

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
