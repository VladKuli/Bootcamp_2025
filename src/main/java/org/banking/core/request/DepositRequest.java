package org.banking.core.request;

import lombok.Getter;

@Getter
public class DepositRequest {

    private String personalCode;
    private Integer amount;

    public DepositRequest(String personalCode, Integer amount) {
        this.personalCode = personalCode;
        this.amount = amount;
    }
}
