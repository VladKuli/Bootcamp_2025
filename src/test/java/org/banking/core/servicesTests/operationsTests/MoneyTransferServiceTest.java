package org.banking.core.servicesTests.operationsTests;


import org.banking.core.database.JpaBankAccountRepository;
import org.banking.core.database.JpaTransactionRepository;
import org.banking.core.domain.BankAccount;
import org.banking.core.domain.IBAN;
import org.banking.core.domain.TransactionType;
import org.banking.core.request.operations.MoneyTransferRequest;
import org.banking.core.response.CoreError;
import org.banking.core.response.operations.MoneyTransferResponse;
import org.banking.core.services.bankAccount.GetCurrentBankAccountService;
import org.banking.core.services.operations.MoneyTransferService;
import org.banking.core.services.validators.operationsValidators.MoneyTransferValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MoneyTransferServiceTest {

    @Mock
    private JpaBankAccountRepository bankAccountRepository;
    @Mock
    private MoneyTransferValidator validator;

    @Mock
    private JpaTransactionRepository transactionRepository;

    @Mock
    private GetCurrentBankAccountService getCurrentBankAccount;

    @InjectMocks
    private MoneyTransferService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldTransferMoneyWhenRequestIsValid() {
        MoneyTransferRequest request = new MoneyTransferRequest(
                "LV-UsersIban",
                "LV-ReceiverIban",
                TransactionType.Other,
                100,
                "Something"
        );

        IBAN senderIban = IBAN.builder()
                .id(0L)
                .ibanNumber("LV-UsersIban")
                .balance(500)
                .build();

        BankAccount senderBankAccount = BankAccount.builder()
                .id(0L)
                .name("Example")
                .surname("Example2")
                .personalCode("12345")
                .build();
        senderBankAccount.setIBAN(List.of(senderIban));
        senderIban.setBankAccount(senderBankAccount);


        IBAN receiverIban = IBAN.builder()
                .id(1L)
                .ibanNumber("LV-ReceiverIban")
                .balance(0)
                .build();

        BankAccount receiverBankAccount = BankAccount.builder()
                .id(1L)
                .name("Example3")
                .surname("Example4")
                .personalCode("54321")
                .build();
        receiverBankAccount.setIBAN(List.of(receiverIban));
        receiverIban.setBankAccount(receiverBankAccount);

        when(getCurrentBankAccount.get()).thenReturn(Optional.of(senderBankAccount));
        when(bankAccountRepository.findByIBANNumber("LV-ReceiverIban")).thenReturn(Optional.of(receiverBankAccount));

        MoneyTransferResponse response = service.execute(request);

        assertNotNull(response);
        assertTrue(response.isCompleted());

        verify(getCurrentBankAccount, times(1)).get();
        verify(bankAccountRepository, times(1)).bankTransfer("LV-UsersIban",
                "LV-ReceiverIban",
                100);
    }




    @Test
    void shouldFailWhenRequestIsNull() {
        MoneyTransferRequest request = new MoneyTransferRequest();
        List<CoreError> mockErrors = List.of(new CoreError());

        when(validator.execute(request)).thenReturn(mockErrors);
        MoneyTransferResponse response = service.execute(request);


        verifyNoInteractions(bankAccountRepository);
        assertFalse(response.isCompleted());
    }
//TODO WRITE TEST CASES FOR EACH FIELD OF REQUEST
}






