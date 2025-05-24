package org.banking.core.services.bankAccount;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.banking.core.database.JpaBankAccountRepository;
import org.banking.core.domain.BankAccount;
import org.banking.core.domain.User;
import org.banking.core.dto.bank_account.BankAccountDTO;
import org.banking.core.mapper.bank_account.BankAccountMapper;
import org.banking.core.request.bankAccount.GetAllBankAccountsRequest;
import org.banking.core.response.bankAccount.GetAllBankAccountsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetAllBankAccountsService {

    private final JpaBankAccountRepository bankAccountRepository;
    private final BankAccountMapper bankAccountMapper;


    public GetAllBankAccountsResponse execute(GetAllBankAccountsRequest request) {
        log.info("Received request to get all bank accounts");

        log.debug("Fetching all bank accounts from the database");
        List<BankAccount> bankAccounts = bankAccountRepository.findAll();

        List<BankAccountDTO> bankAccountDTOS = bankAccounts.stream()
                .map(bankAccountMapper::toDto)
                .collect(Collectors.toList());

        log.info("Successfully retrieved {} bank accounts", bankAccounts.size());
        return new GetAllBankAccountsResponse(bankAccountDTOS);
    }
}
