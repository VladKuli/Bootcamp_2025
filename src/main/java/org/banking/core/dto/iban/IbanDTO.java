package org.banking.core.dto.iban;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IbanDTO {


    @NotNull(message = "Iban number cannot be null")
    @NotEmpty(message = "Iban number cannot be empty")
    private String ibanNumber;

    @NotNull
    private Integer balance;

    @NotNull
    private List<String> cardNumbers;
}
