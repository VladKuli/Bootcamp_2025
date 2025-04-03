package org.banking.core.response.bankAccount;

import lombok.Getter;
import org.banking.core.domain.BankAccount;
import org.banking.core.dto.bank_account.BankAccountDTO;

import java.util.List;

@Getter
public class GetAllBankAccountsResponse {

    private List<BankAccountDTO> bankAccountList;

    public GetAllBankAccountsResponse(List<BankAccountDTO> bankAccountList) {
        this.bankAccountList = bankAccountList;
    }
}
