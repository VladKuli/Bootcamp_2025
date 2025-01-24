package org.banking.core.services;

import org.banking.core.database.JpaBankAccountRepository;
import org.banking.core.domain.BankAccount;
import org.banking.core.request.GetAllBankAccountsRequest;
import org.banking.core.response.GetAllBankAccountsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAllBankAccountsService {

    @Autowired
    private JpaBankAccountRepository bankAccountRepository;

    public GetAllBankAccountsResponse execute(GetAllBankAccountsRequest request) {
        List<BankAccount> bankAccounts = bankAccountRepository.findAll();
        return new GetAllBankAccountsResponse(bankAccounts);
    }
}
