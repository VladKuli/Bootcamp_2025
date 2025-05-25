package org.banking.core.dto.card;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.banking.core.enums.TypeOfTheCard;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardDTO {

    @NotNull(message = "Card number cannot be null")
    @NotEmpty(message = "Card number cannot be empty")
    private String cardNumber;

    @NotNull
    private Integer balance;

    @NotNull
    private TypeOfTheCard type;

    @NotNull(message = "Iban number cannot be null")
    @NotEmpty(message = "Iban number cannot be empty")
    private String IbanNumber;
}
