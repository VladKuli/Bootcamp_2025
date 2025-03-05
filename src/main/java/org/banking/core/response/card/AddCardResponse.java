package org.banking.core.response.card;

import lombok.Getter;
import lombok.Setter;
import org.banking.core.response.CoreError;
import org.banking.core.response.CoreResponse;

import java.util.List;

@Getter
@Setter
public class AddCardResponse extends CoreResponse {

    private boolean isOrdered;

    public AddCardResponse(List<CoreError> errorList) {
        super(errorList);
    }

    public AddCardResponse(boolean isOrdered) {
        this.isOrdered = isOrdered;
    }
}
