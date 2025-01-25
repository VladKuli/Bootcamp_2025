package org.banking.core.request.operations;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class SeeYourBalanceRequest {

    private String personalCode;

    public SeeYourBalanceRequest(String personalCode) {
        this.personalCode = personalCode;
    }

}
