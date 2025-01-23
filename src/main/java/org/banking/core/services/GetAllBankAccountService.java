package org.banking.core.services;

import org.banking.core.database.JpaBankAccountRepository;
import org.banking.core.domain.BankAccount;
import org.banking.core.request.GetAllBankAccountRequest;
import org.banking.core.response.GetAllBankAccountResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAllBankAccountService {

    @Autowired
    private JpaBankAccountRepository bankAccountRepository;

    public GetAllBankAccountResponse execute(GetAllBankAccountRequest request) {
        List<BankAccount> bankAccounts = bankAccountRepository.findAll();
        return new GetAllBankAccountResponse(bankAccounts);
    }
}
