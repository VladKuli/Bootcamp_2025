package org.banking.core.response.bankAccount;

import lombok.Getter;
import org.banking.core.dto.bank_account.BankAccountDTO;
import org.banking.core.response.CoreError;
import org.banking.core.response.CoreResponse;

import java.util.List;

@Getter
public class AddBankAccountResponse extends CoreResponse {

    private BankAccountDTO bankAccountDto;

    public AddBankAccountResponse(List<CoreError> errorList) {
        super(errorList);
    }

    public AddBankAccountResponse(BankAccountDTO bankAccountDto) {
        this.bankAccountDto = bankAccountDto;
    }
}
