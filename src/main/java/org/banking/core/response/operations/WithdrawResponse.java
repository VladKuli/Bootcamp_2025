package org.banking.core.response.operations;

import lombok.Getter;
import org.banking.core.response.CoreError;
import org.banking.core.response.CoreResponse;

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
