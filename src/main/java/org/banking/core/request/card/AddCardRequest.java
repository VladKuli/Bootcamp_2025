package org.banking.core.request.card;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.banking.core.enums.TypeOfTheCard;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddCardRequest {

    private String iban;
    private TypeOfTheCard type;
}
