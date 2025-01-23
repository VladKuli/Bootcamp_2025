package org.banking.core.request;

import lombok.Getter;

@Getter
public class WithdrawRequest {

    private String personalCode;
    private double amount;

    public WithdrawRequest(String personalCode, double amount) {
        this.personalCode = personalCode;
        this.amount = amount;
    }
}
