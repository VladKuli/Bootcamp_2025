package org.banking.core.servicesTests.operationsTests;


import org.banking.core.database.JpaBankAccountRepository;
import org.banking.core.request.operations.WithdrawRequest;
import org.banking.core.response.CoreError;
import org.banking.core.response.operations.WithdrawResponse;
import org.banking.core.services.operations.WithdrawService;
import org.banking.core.services.user.GetCurrentUserPersonalCodeService;
import org.banking.core.services.validators.WithdrawValidator;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WithdrawServiceTest {

    @Mock
    private JpaBankAccountRepository bankAccountRepository;

    @Mock
    private GetCurrentUserPersonalCodeService personalCodeService;

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
        WithdrawRequest request = new WithdrawRequest(100);
        List<CoreError> noErrors = Collections.emptyList();

        when(validator.validate(request)).thenReturn(noErrors);
        when(personalCodeService.getCurrentUserPersonalCode()).thenReturn("1234567890");

        WithdrawResponse response = service.execute(request);

        assertNotNull(response);
        assertTrue(response.isCompleted());

        verify(validator, times(1)).validate(request);
        verify(personalCodeService, times(1)).getCurrentUserPersonalCode();
        verify(bankAccountRepository, times(1)).withdraw("1234567890", 100);
    }

    @Test
    void shouldReturnErrorsWhenRequestIsInvalid() {
        WithdrawRequest request = new WithdrawRequest(-100);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Amount must be positive."));

        when(validator.validate(request)).thenReturn(errors);

        WithdrawResponse response = service.execute(request);

        assertNotNull(response);
        assertFalse(response.isCompleted());
        assertNotNull(response.getErrors());
        assertEquals(1, response.getErrors().size());
        assertEquals("Amount must be positive.", response.getErrors().get(0).getMessage());

        verify(validator, times(1)).validate(request);
        verifyNoInteractions(personalCodeService);
        verifyNoInteractions(bankAccountRepository);
    }
}

