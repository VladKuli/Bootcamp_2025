package org.banking.core.servicesTests.operationsTests;

/*
import org.banking.core.database.JpaBankAccountRepository;
import org.banking.core.request.operations.MoneyTransferRequest;
import org.banking.core.response.CoreError;
import org.banking.core.response.operations.MoneyTransferResponse;
import org.banking.core.services.operations.MoneyTransferService;
import org.banking.core.services.user.GetCurrentUserPersonalCodeService;
import org.banking.core.services.validators.operationsValidators.MoneyTransferValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MoneyTransferServiceTest {

    @Mock
    private JpaBankAccountRepository bankAccountRepository;
    @Mock
    private MoneyTransferValidator validator;

    @Mock
    private GetCurrentUserPersonalCodeService personalCodeService;

    @InjectMocks
    private MoneyTransferService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
/*
    @Test
    void shouldTransferMoneySuccessfully() {
        MoneyTransferRequest request = new MoneyTransferRequest("0987654321", 200);

        when(personalCodeService.getCurrentUserPersonalCode()).thenReturn("1234567890");
        MoneyTransferResponse response = service.execute(request);

        assertNotNull(response);
        assertTrue(response.isCompleted());

        verify(personalCodeService, times(1)).getCurrentUserPersonalCode();
        verify(bankAccountRepository, times(1)).bankTransfer("1234567890",
                "0987654321", 200);
    }



    @Test
    void shouldFailWhenTargetPersonalCodeIsNull() {
        MoneyTransferRequest request = new MoneyTransferRequest(null, 200);
        List<CoreError> mockErrors = List.of(new CoreError());

        when(validator.execute(request)).thenReturn(mockErrors);
        MoneyTransferResponse response = service.execute(request);


        verifyNoInteractions(bankAccountRepository);
        assertFalse(response.isCompleted());
    }

    @Test
    void shouldFailWhenAmountIsZero() {
        MoneyTransferRequest request = new MoneyTransferRequest("0987654321", 0);
        List<CoreError> mockErrors = List.of(new CoreError());

        when(validator.execute(request)).thenReturn(mockErrors);
        MoneyTransferResponse response = service.execute(request);

        verifyNoInteractions(bankAccountRepository);
        assertFalse(response.isCompleted());
    }
}

 */


