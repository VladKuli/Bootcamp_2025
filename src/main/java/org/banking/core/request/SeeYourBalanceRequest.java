package org.banking.core.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.banking.core.domain.BankAccount;

import java.util.Optional;
@NoArgsConstructor
@Getter
public class SeeYourBalanceRequest {

    private String personalCode;

    public SeeYourBalanceRequest(String personalCode) {
        this.personalCode = personalCode;
    }

}
