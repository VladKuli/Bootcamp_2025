package org.banking.core.request;

import lombok.Getter;

@Getter
public class WithdrawRequest {

    private String personalCode;
    private Integer amount;

    public WithdrawRequest(String personalCode, Integer amount) {
        this.personalCode = personalCode;
        this.amount = amount;
    }
}
