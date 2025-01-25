package org.banking.core.servicesTests;

import org.banking.core.database.JpaBankAccountRepository;
import org.banking.core.domain.BankAccount;
import org.banking.core.request.bankAccount.AddBankAccountRequest;
import org.banking.core.response.bankAccount.AddBankAccountResponse;
import org.banking.core.response.CoreError;
import org.banking.core.services.bankAccount.AddBankAccountService;
import org.banking.core.services.validators.AddBankAccountValidator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AddBankAccountServiceTest {

    @Mock
    private JpaBankAccountRepository bankAccountRepository;

    @Mock
    private AddBankAccountValidator validator;

    @InjectMocks
    private AddBankAccountService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldAddBankAccountWhenRequestIsValid() {
        AddBankAccountRequest request = new AddBankAccountRequest("John", "Doe", "1234567890");
        List<CoreError> noErrors = Collections.emptyList();

        when(validator.validate(request)).thenReturn(noErrors);

        BankAccount savedBankAccount = new BankAccount("John", "Doe", "1234567890");
        when(bankAccountRepository.save(any(BankAccount.class))).thenReturn(savedBankAccount);

        AddBankAccountResponse response = service.execute(request);

        assertNotNull(response.getBankAccount());
        assertEquals("John", response.getBankAccount().getName());
        assertEquals("Doe", response.getBankAccount().getSurname());
        assertEquals("1234567890", response.getBankAccount().getPersonalCode());

        verify(validator, times(1)).validate(request);
        verify(bankAccountRepository, times(1)).save(any(BankAccount.class));
    }

    @Test
    void shouldReturnErrorsWhenRequestIsInvalid() {
        AddBankAccountRequest request = new AddBankAccountRequest("", "Doe", "");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Name field must be filled"));
        errors.add(new CoreError("Personal code field must be filled"));

        when(validator.validate(request)).thenReturn(errors);

        AddBankAccountResponse response = service.execute(request);

        assertNotNull(response);
        assertNull(response.getBankAccount());
        assertNotNull(response.getErrors());
        assertEquals(2, response.getErrors().size());
        assertEquals("Name field must be filled", response.getErrors().get(0).getMessage());
        assertEquals("Personal code field must be filled", response.getErrors().get(1).getMessage());

        verify(validator, times(1)).validate(request);
        verifyNoInteractions(bankAccountRepository);
    }
}
