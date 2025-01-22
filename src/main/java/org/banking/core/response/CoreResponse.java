package org.banking.core.response;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CoreResponse extends CoreError{

    private List<CoreError> errorList = new ArrayList<>();

    public boolean hasErrors() {
        return this.errorList != null;
    }
}
