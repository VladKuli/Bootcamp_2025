package org.banking.core.servicesTests.operationsTests;


import org.banking.core.database.JpaBankAccountRepository;
import org.banking.core.domain.BankAccount;
import org.banking.core.domain.IBAN;
import org.banking.core.request.operations.DepositRequest;
import org.banking.core.request.operations.WithdrawRequest;
import org.banking.core.response.CoreError;
import org.banking.core.response.operations.WithdrawResponse;
import org.banking.core.services.bankAccount.GetCurrentBankAccountService;
import org.banking.core.services.operations.WithdrawService;
import org.banking.core.services.user.GetCurrentUserPersonalCodeService;
import org.banking.core.services.validators.operationsValidators.WithdrawValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WithdrawServiceTest {

    @Mock
    private JpaBankAccountRepository bankAccountRepository;

    @Mock
    private GetCurrentBankAccountService getCurrentBankAccountService;

    @Mock
    private WithdrawValidator validator;

    @InjectMocks
    private WithdrawService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldWithdrawWhenRequestIsValid() {
        WithdrawRequest request = new WithdrawRequest("LV-Example",100);

        BankAccount savedBankAccount = BankAccount.builder()
                .name("Example")
                .surname("Example 2")
                .personalCode("1234567890")
                .build();
        IBAN iban = IBAN.builder()
                .id(0L)
                .ibanNumber("LV-Example")
                .balance(0)
                .bankAccount(savedBankAccount)
                .build();
        savedBankAccount.setIBAN(List.of(iban));

        when(validator.validate(request)).thenReturn(List.of());
        when(getCurrentBankAccountService.get()).thenReturn(Optional.of(savedBankAccount));
        WithdrawResponse response = service.execute(request);

        assertNotNull(response);
        assertTrue(response.isCompleted());

        verify(validator, times(1)).validate(request);
        verify(getCurrentBankAccountService, times(1)).get();
        verify(bankAccountRepository, times(1)).deductBalanceForIban(100, "LV-Example");
    }

    @Test
    void shouldReturnErrorsWhenRequestIsInvalid() {
        WithdrawRequest request = new WithdrawRequest("LV-Example",-100);
        BankAccount savedBankAccount = BankAccount.builder()
                .name("Example")
                .surname("Example 2")
                .personalCode("1234567890")
                .build();
        IBAN iban = IBAN.builder()
                .id(0L)
                .ibanNumber("LV-Example")
                .balance(0)
                .bankAccount(savedBankAccount)
                .build();
        savedBankAccount.setIBAN(List.of(iban));

        when(validator.validate(request)).thenReturn(List.of(new CoreError("Amount of money should be positive")));
        WithdrawResponse response = service.execute(request);

        assertNotNull(response);
        verify(validator, times(1)).validate(request);
        verifyNoInteractions(getCurrentBankAccountService);
        verifyNoInteractions(bankAccountRepository);
    }

}