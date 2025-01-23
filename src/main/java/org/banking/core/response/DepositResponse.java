package org.banking.core.response;

import lombok.Getter;

import java.util.List;

@Getter
public class DepositResponse extends CoreResponse {

    private boolean isCompleted;

    public DepositResponse(List<CoreError> errorList) {
        super(errorList);
    }

    public DepositResponse(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }
}
