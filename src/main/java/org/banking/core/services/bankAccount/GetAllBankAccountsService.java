package org.banking.core.services.bankAccount;

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

@Service
public class GetAllBankAccountsService {

    @Autowired
    private JpaBankAccountRepository bankAccountRepository;
    @Autowired
    private BankAccountMapper bankAccountMapper;

    private static final Logger logger = LoggerFactory.getLogger(GetAllBankAccountsService.class);

    public GetAllBankAccountsResponse execute(GetAllBankAccountsRequest request) {
        logger.info("Received request to get all bank accounts");

        logger.debug("Fetching all bank accounts from the database");
        List<BankAccount> bankAccounts = bankAccountRepository.findAll();

        List<BankAccountDTO> bankAccountDTOS = bankAccounts.stream()
                .map(bankAccount -> bankAccountMapper.toDto(bankAccount))
                .collect(Collectors.toList());

        logger.info("Successfully retrieved {} bank accounts", bankAccounts.size());
        return new GetAllBankAccountsResponse(bankAccountDTOS);
    }
}
