package org.banking.core.servicesTests.operationsTests;
/*

import org.banking.core.database.JpaBankAccountRepository;
import org.banking.core.domain.BankAccount;
import org.banking.core.domain.Card;
import org.banking.core.domain.IBAN;
import org.banking.core.enums.TypeOfTheCard;
import org.banking.core.request.operations.DepositRequest;
import org.banking.core.response.CoreError;
import org.banking.core.response.operations.DepositResponse;
import org.banking.core.services.bankAccount.GetCurrentBankAccountService;
import org.banking.core.services.iban.CurrentUserIbanService;
import org.banking.core.services.operations.DepositService;
import org.banking.core.services.user.GetCurrentUserPersonalCodeService;
import org.banking.core.services.validators.operationsValidators.DepositValidator;
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
class DepositServiceTest {

    @Mock
    private JpaBankAccountRepository bankAccountRepository;

    @Mock
    private DepositValidator validator;

    @Mock
    private GetCurrentBankAccountService personalCodeService;

    @InjectMocks
    private DepositService service;

    @Mock
    private CurrentUserIbanService ibanService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldDepositWhenRequestIsValid() {
        DepositRequest request = DepositRequest.builder()
                .amount(100)
                .IBAN("LV-Example")
                .build();
        List<CoreError> noErrors = Collections.emptyList();

        BankAccount savedBankAccount = BankAccount.builder()
                .name("John")
                .surname("Doe")
                .personalCode("1234567890")
                .build();
        IBAN iban = IBAN.builder()
                .id(0L)
                .ibanNumber("LV-Example")
                .balance(0)
                .bankAccount(savedBankAccount)
                .build();
        savedBankAccount.setIBAN(List.of(iban));

        when(validator.validate(request)).thenReturn(noErrors);
        when(personalCodeService.get()).thenReturn(Optional.of(savedBankAccount));

        DepositResponse response = service.execute(request);

        assertNotNull(response);
        assertTrue(response.isCompleted());

        verify(validator, times(1)).validate(request);
        verify(bankAccountRepository, times(1)).ibanDeposit(0L, 100);
    }


    @Test
    void shouldReturnErrorsWhenRequestIsInvalid() {
        DepositRequest request =  DepositRequest.builder()
                .IBAN("LV-Example")
                .amount(-100).build();
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Amount must be greater than 0"));

        when(validator.validate(request)).thenReturn(errors);

        DepositResponse response = service.execute(request);

        assertNotNull(response);
        assertFalse(response.isCompleted());
        assertNotNull(response.getErrors());
        assertEquals(1, response.getErrors().size());
        assertEquals("Amount must be greater than 0", response.getErrors().get(0).getMessage());

        verify(validator, times(1)).validate(request);
        verifyNoInteractions(personalCodeService);
        verifyNoInteractions(bankAccountRepository);
    }

}

 */