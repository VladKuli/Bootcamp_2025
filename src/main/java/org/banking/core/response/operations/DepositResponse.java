package org.banking.core.response.operations;

import lombok.Getter;
import org.banking.core.response.CoreError;
import org.banking.core.response.CoreResponse;

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
