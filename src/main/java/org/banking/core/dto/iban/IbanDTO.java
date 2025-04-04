package org.banking.core.dto.iban;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IbanDTO {

    private String ibanNumber;
    private Integer balance;
    private List<String> cardNumbers;
}
