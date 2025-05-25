package org.banking.core.dto.bank_account;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BankAccountDTO {

    @NotNull(message = "Name cannot be null")
    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @NotNull(message = "Surname cannot be null")
    @NotEmpty(message = "Surname cannot be empty")
    private String surname;

    @NotNull
    @NotEmpty
    private String balance;

    @NotNull(message = "Personal code cannot be null")
    @NotEmpty(message = "Personal code cannot be empty")
    private String personalCode;
    @NotNull
    private List<String> ibanNumbers;
}
