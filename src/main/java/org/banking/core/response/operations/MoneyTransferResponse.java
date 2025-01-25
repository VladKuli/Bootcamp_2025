package org.banking.core.response.operations;

import lombok.Getter;
import org.banking.core.response.CoreError;
import org.banking.core.response.CoreResponse;

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
