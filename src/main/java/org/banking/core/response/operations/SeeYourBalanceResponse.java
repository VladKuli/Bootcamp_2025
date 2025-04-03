package org.banking.core.response.operations;

import lombok.Getter;
import org.banking.core.domain.BankAccount;
import org.banking.core.domain.IBAN;
import org.banking.core.dto.bank_account.BankAccountDTO;
import org.banking.core.response.CoreError;
import org.banking.core.response.CoreResponse;

import java.util.List;
import java.util.Optional;

@Getter
public class SeeYourBalanceResponse extends CoreResponse {

    private BankAccountDTO bankAccountDTO;

    public SeeYourBalanceResponse(List<CoreError> errorList) {
        super(errorList);
    }

    public SeeYourBalanceResponse(BankAccountDTO bankAccountDTO) {
        this.bankAccountDTO = bankAccountDTO;
    }
}
