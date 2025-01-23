package org.banking.core.request;

import lombok.Getter;

@Getter
public class DepositRequest {

    private String personalCode;
    private double amount;

    public DepositRequest(String personalCode, double amount) {
        this.personalCode = personalCode;
        this.amount = amount;
    }
}
