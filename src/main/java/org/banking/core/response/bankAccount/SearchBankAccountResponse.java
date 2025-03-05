package org.banking.core.response.bankAccount;

import lombok.Getter;
import org.banking.core.domain.BankAccount;
import org.banking.core.response.CoreError;
import org.banking.core.response.CoreResponse;

import java.util.List;

@Getter
public class SearchBankAccountResponse extends CoreResponse {
    private List<BankAccount> bankAccountList;

    public SearchBankAccountResponse(List<CoreError> errorList, List<BankAccount> bankAccountList) {
        super(errorList);
        this.bankAccountList = bankAccountList;
    }


}
