package org.banking.core.response;

import lombok.Getter;

import java.util.List;

@Getter
public class WithdrawResponse extends CoreResponse {

    private boolean isCompleted;

    public WithdrawResponse(List<CoreError> errorList) {
        super(errorList);
    }

    public WithdrawResponse(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }
}
