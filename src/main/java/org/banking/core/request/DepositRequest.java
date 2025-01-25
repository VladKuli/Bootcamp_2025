package org.banking.core.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DepositRequest {

    private String personalCode;
    private int amount;

    public DepositRequest(String personalCode, Integer amount) {
        this.personalCode = personalCode;
        this.amount = amount;
    }

    public DepositRequest(Integer amount) {
        this.amount = amount;
    }
}
