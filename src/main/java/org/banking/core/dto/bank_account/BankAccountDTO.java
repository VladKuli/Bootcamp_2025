package org.banking.core.dto.bank_account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BankAccountDTO {

    private String name;
    private String surname;
    private String balance;
    private String personalCode;
    private List<String> ibanNumbers;
}
