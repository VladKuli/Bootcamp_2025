package org.banking.core.request.bankAccount;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DeleteBankAccountRequest {

    private String personalCode;
}
