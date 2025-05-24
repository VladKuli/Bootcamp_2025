package org.banking.core.servicesTests.bankAccountTests;

import org.banking.core.database.JpaBankAccountRepository;
import org.banking.core.request.bankAccount.DeleteBankAccountRequest;
import org.banking.core.response.CoreError;
import org.banking.core.response.bankAccount.DeleteBankAccountResponse;
import org.banking.core.services.bankAccount.DeleteBankAccountService;
import org.banking.core.services.validators.bankAccountValidators.DeleteBankAccountValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Disabled
class DeleteBankAccountServiceTest {

    @Mock
    private JpaBankAccountRepository bankAccountRepository;

    @Mock
    private DeleteBankAccountValidator validator;

    @InjectMocks
    private DeleteBankAccountService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldDeleteBankAccountWhenRequestIsValid() {
        DeleteBankAccountRequest request = new DeleteBankAccountRequest("1234567890");
        List<CoreError> noErrors = Collections.emptyList();

        when(validator.validate(request)).thenReturn(noErrors);

        DeleteBankAccountResponse response = service.execute(request);

        assertNotNull(response);
        assertTrue(response.isDeleted());

        verify(validator, times(1)).validate(request);
        verify(bankAccountRepository, times(1)).deleteByPersonalCode("1234567890");
    }

    @Test
    void shouldReturnErrorsWhenRequestIsInvalid() {
        DeleteBankAccountRequest request = new DeleteBankAccountRequest("");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Personal code must not be empty"));

        when(validator.validate(request)).thenReturn(errors);

        DeleteBankAccountResponse response = service.execute(request);

        assertNotNull(response);
        assertFalse(response.isDeleted());
        assertNotNull(response.getErrors());
        assertEquals(1, response.getErrors().size());
        assertEquals("Personal code must not be empty", response.getErrors().get(0).getMessage());

        verify(validator, times(1)).validate(request);
        verifyNoInteractions(bankAccountRepository);
    }
}
