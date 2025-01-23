package org.banking.core.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.banking.core.domain.BankAccount;

import java.util.Optional;
@NoArgsConstructor
@Getter
public class SeeYourBalanceRequest {

    private Optional<BankAccount> bankAccount;

    public SeeYourBalanceRequest(Optional<BankAccount> bankAccount) {
        this.bankAccount = bankAccount;
    }
}
