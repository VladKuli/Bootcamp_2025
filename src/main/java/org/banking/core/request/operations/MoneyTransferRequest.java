package org.banking.core.request.operations;

import lombok.*;
import org.banking.core.domain.TransactionType;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MoneyTransferRequest {

    private String usersIban;
    private String cardNumber;
    private String targetIBAN;
    private TransactionType type;
    private int amount;
    private String description;

}
