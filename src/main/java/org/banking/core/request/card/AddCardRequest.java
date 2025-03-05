package org.banking.core.request.card;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.banking.core.domain.TypeOfTheCard;

import javax.management.MXBean;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddCardRequest {

    private TypeOfTheCard type;
}
