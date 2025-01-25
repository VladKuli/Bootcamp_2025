package org.banking.core.request.operations;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MoneyTransferRequest {

    private String targetPersonalCode;
    private int amount;


}
