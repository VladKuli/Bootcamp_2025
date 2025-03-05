package org.banking.core.response.card;

import org.banking.core.response.CoreError;
import org.banking.core.response.CoreResponse;

import java.util.List;

public class DeleteCardResponse extends CoreResponse {

    private boolean isCompleted;

    public DeleteCardResponse(List<CoreError> errorList) {
        super(errorList);
    }

    public DeleteCardResponse(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }
}
