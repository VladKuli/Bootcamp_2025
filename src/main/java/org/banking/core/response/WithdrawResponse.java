package org.banking.core.response;

import lombok.Getter;

import java.util.List;

@Getter
public class WithdrawResponse extends CoreResponse {

    private boolean isCompleted;

    public WithdrawResponse(List<CoreError> errorList, boolean isCompleted) {
        super(errorList);
        this.isCompleted = isCompleted;
    }

    public WithdrawResponse(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }
}
