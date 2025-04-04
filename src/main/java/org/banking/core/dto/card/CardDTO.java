package org.banking.core.dto.card;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.banking.core.enums.TypeOfTheCard;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardDTO {

    private String cardNumber;

    private Integer balance;

    private TypeOfTheCard type;

    private String IbanNumber;
}
