package org.banking.core.services.transaction;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.banking.core.domain.BankAccount;
import org.banking.core.domain.Transaction;
import org.banking.core.dto.transaction.TransactionDTO;
import org.banking.core.mapper.transaction.TransactionMapper;
import org.banking.core.services.bankAccount.GetCurrentBankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CurrentUserTransactionsService {

    private final GetCurrentBankAccountService getCurrentBankAccountService;

    private final TransactionMapper transactionMapper;


    public List<TransactionDTO> getBankAccountIncomingTransactions() {

        Optional<BankAccount> bankAccount = getCurrentBankAccountService.get();

        List<Transaction> transactions = bankAccount.get().getIncomingTransactions();
        List<TransactionDTO> transactionDTOS = new ArrayList<>();

        for (Transaction transaction : transactions) {
            transactionDTOS.add(transactionMapper.toDto(transaction));
        }

        for (TransactionDTO transactionDTO : transactionDTOS) {
            for (Transaction transaction : transactions) {
                transactionDTO.setFromAccountName(transaction.getFromAccount().getName());
                transactionDTO.setFromAccountSurname(transaction.getFromAccount().getSurname());
                transactionDTO.setToAccountName(transaction.getToAccount().getName());
                transactionDTO.setToAccountSurname(transaction.getToAccount().getSurname());

            }
        }

        return transactionDTOS;
    }

    public  List<TransactionDTO> getBankAccountOutgoingTransactions() {

        Optional<BankAccount> bankAccount = getCurrentBankAccountService.get();

        List<Transaction> transactions = bankAccount.get().getOutgoingTransactions();
        List<TransactionDTO> transactionDTOS = new ArrayList<>();

        for (Transaction transaction : transactions) {
            transactionDTOS.add(transactionMapper.toDto(transaction));
        }

        for (TransactionDTO transactionDTO : transactionDTOS) {
            for (Transaction transaction : transactions) {
                transactionDTO.setFromAccountName(transaction.getFromAccount().getName());
                transactionDTO.setFromAccountSurname(transaction.getFromAccount().getSurname());
                transactionDTO.setToAccountName(transaction.getToAccount().getName());
                transactionDTO.setToAccountSurname(transaction.getToAccount().getSurname());

            }
        }

        return transactionDTOS;
    }

}
