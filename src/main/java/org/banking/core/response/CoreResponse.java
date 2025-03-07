package org.banking.core.response;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public abstract class CoreResponse extends CoreError{

    private List<CoreError> errorList = new ArrayList<>();

    public boolean hasErrors() {
        return this.errorList != null;
    }

    public List<CoreError> getErrors() {
        return errorList;
    }

}
