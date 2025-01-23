package org.banking.core.response;

import org.banking.core.domain.BankAccount;

import java.util.List;

public class GetAllBankAccountResponse {

    private List<BankAccount> bankAccountList;

    public GetAllBankAccountResponse(List<BankAccount> bankAccountList) {
        this.bankAccountList = bankAccountList;
    }
}
