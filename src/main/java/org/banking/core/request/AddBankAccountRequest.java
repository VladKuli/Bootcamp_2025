package org.banking.core.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class AddBankAccountRequest {

    private String name;
    private String surname;
    private String personalCode;
    private Integer balance;

}


