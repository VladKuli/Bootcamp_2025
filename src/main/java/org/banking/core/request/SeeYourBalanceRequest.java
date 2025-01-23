package org.banking.core.request;

import lombok.Getter;
import org.banking.core.domain.BankAccount;

import java.util.Optional;

@Getter
public class SeeYourBalanceRequest {

    private Optional<BankAccount> bankAccount;

    public SeeYourBalanceRequest(Optional<BankAccount> bankAccount) {
        this.bankAccount = bankAccount;
    }
}
