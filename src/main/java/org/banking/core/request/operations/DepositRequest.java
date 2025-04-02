package org.banking.core.request.operations;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepositRequest {

    private String personalCode;
    private String IBAN;
    private int amount;

}
