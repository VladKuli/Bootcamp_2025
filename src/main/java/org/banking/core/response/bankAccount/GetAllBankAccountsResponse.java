package org.banking.core.response.bankAccount;

import lombok.Getter;
import org.banking.core.domain.BankAccount;

import java.util.List;

@Getter
public class GetAllBankAccountsResponse {

    private List<BankAccount> bankAccountList;

    public GetAllBankAccountsResponse(List<BankAccount> bankAccountList) {
        this.bankAccountList = bankAccountList;
    }
}
