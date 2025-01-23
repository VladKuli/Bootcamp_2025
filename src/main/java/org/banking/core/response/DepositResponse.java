package org.banking.core.response;

import lombok.Getter;

import java.util.List;

@Getter
public class DepositResponse extends CoreResponse {

    private boolean isCompleted;

    public DepositResponse(List<CoreError> errorList, boolean isCompleted) {
        super(errorList);
        this.isCompleted = isCompleted;
    }

    public DepositResponse(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }
}
