package org.banking.core.response;

import lombok.Getter;

import java.util.List;

@Getter
public class MoneyTransferResponse extends CoreResponse {

    private boolean isCompleted;

    public MoneyTransferResponse(List<CoreError> errorList) {
        super(errorList);
    }

    public MoneyTransferResponse(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }
}
