package org.banking.core.services.bankAccount;

import org.banking.core.database.JpaBankAccountRepository;
import org.banking.core.domain.BankAccount;
import org.banking.core.domain.IBAN;
import org.banking.core.domain.Transaction;
import org.banking.core.dto.bank_account.BankAccountDTO;
import org.banking.core.dto.iban.IbanDTO;
import org.banking.core.dto.transaction.TransactionDTO;
import org.banking.core.mapper.bank_account.BankAccountMapper;
import org.banking.core.mapper.iban.IbanMapper;
import org.banking.core.mapper.transaction.TransactionMapper;
import org.banking.core.response.bankAccount.GetCurrentBankAccountResponse;
import org.banking.core.services.user.GetCurrentUserPersonalCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GetCurrentBankAccountService {

    @Autowired
    private JpaBankAccountRepository jpa;

    @Autowired
    private GetCurrentUserPersonalCodeService getUser;

    @Autowired
    private IbanMapper ibanMapper;

    @Autowired
    private BankAccountMapper bankAccountMapper;

    @Autowired
    private TransactionMapper transactionMapper;

    public Optional<BankAccount> get() {

        String personalCode = getUser.getCurrentUserPersonalCode();

        return !jpa.findByPersonalCode(personalCode).isEmpty()
                ? jpa.findByPersonalCode(personalCode).stream().findFirst()
                : Optional.empty();
    }

    public List<IBAN> getIBAN() {
        String personalCode = getUser.getCurrentUserPersonalCode();

        return jpa.findByPersonalCode(personalCode).stream()
                .flatMap(bankAccount -> bankAccount.getIBAN().stream())
                .toList();
    }


    public List<IbanDTO> getIbanDTO() {
        String personalCode = getUser.getCurrentUserPersonalCode();

        List<IBAN> ibanList = jpa.findByPersonalCode(personalCode).stream()
                .flatMap(bankAccount -> bankAccount.getIBAN().stream())
                .toList();

        return ibanList.stream()
                .map(iban -> ibanMapper.toDto(iban)).collect(Collectors.toList());
    }

    public BankAccountDTO getBankAccountDTO() {

        String personalCode = getUser.getCurrentUserPersonalCode();

        Optional<BankAccount> bankAccount = Optional.empty();
        if (!jpa.findByPersonalCode(personalCode).isEmpty()) {
            bankAccount = jpa.findByPersonalCode(personalCode).stream().findFirst();
        }

        return bankAccountMapper.toDto(bankAccount.get());
    }
    public  List<TransactionDTO> getBankAccountIncomingTransactions() {

        String personalCode = getUser.getCurrentUserPersonalCode();

        Optional<BankAccount> bankAccount = Optional.empty();
        if (!jpa.findByPersonalCode(personalCode).isEmpty()) {
            bankAccount = jpa.findByPersonalCode(personalCode).stream().findFirst();
        }

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

        String personalCode = getUser.getCurrentUserPersonalCode();

        Optional<BankAccount> bankAccount = Optional.empty();
        if (!jpa.findByPersonalCode(personalCode).isEmpty()) {
            bankAccount = jpa.findByPersonalCode(personalCode).stream().findFirst();
        }

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
