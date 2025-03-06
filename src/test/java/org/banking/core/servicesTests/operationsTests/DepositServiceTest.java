package org.banking.core.servicesTests.operationsTests;
/*

import org.banking.core.database.JpaBankAccountRepository;
import org.banking.core.request.operations.DepositRequest;
import org.banking.core.response.CoreError;
import org.banking.core.response.operations.DepositResponse;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DepositServiceTest {

    @Mock
    private JpaBankAccountRepository bankAccountRepository;

    @Mock
    private DepositValidator validator;

    @Mock
    private GetCurrentUserPersonalCodeService personalCodeService;

    @InjectMocks
    private DepositService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldDepositWhenRequestIsValid() {
        DepositRequest request = DepositRequest.builder()
                .personalCode("1234567890")
                .amount(100).build();
        List<CoreError> noErrors = Collections.emptyList();

        when(validator.validate(request)).thenReturn(noErrors);
        when(personalCodeService.getCurrentUserPersonalCode()).thenReturn("1234567890");

        DepositResponse response = service.execute(request);

        assertNotNull(response);
        assertTrue(response.isCompleted());

        verify(validator, times(1)).validate(request);
        verify(personalCodeService, times(1)).getCurrentUserPersonalCode();
        verify(bankAccountRepository, times(1)).deposit("1234567890", 100);
    }

    @Test
    void shouldReturnErrorsWhenRequestIsInvalid() {
        DepositRequest request =  DepositRequest.builder()
                .personalCode("1234567890")
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
