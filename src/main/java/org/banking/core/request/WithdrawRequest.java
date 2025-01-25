package org.banking.core.request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WithdrawRequest {

    private String personalCode;
    private int amount;

}
