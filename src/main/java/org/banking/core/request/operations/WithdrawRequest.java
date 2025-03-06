package org.banking.core.request.operations;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WithdrawRequest {

    private String cardNumber;
    private int amount;

}
