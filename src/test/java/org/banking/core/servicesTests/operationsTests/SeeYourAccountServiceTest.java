package org.banking.core.servicesTests.operationsTests;
/*

import org.banking.core.database.JpaBankAccountRepository;
import org.banking.core.domain.BankAccount;
import org.banking.core.request.operations.SeeYourBalanceRequest;
import org.banking.core.response.operations.SeeYourBalanceResponse;
import org.banking.core.services.operations.SeeYourBalanceService;
import org.banking.core.services.user.GetCurrentUserPersonalCodeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SeeYourAccountServiceTest {

    @Mock
    private JpaBankAccountRepository bankAccountRepository;

    @Mock
    private GetCurrentUserPersonalCodeService personalCodeService;

    @InjectMocks
    private SeeYourBalanceService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnBalanceSuccessfully() {
        String personalCode = "1234567890";
        BankAccount bankAccount = BankAccount.builder()
                .name("John")
                .surname("Doe")
                .personalCode(personalCode)
                .build();

        when(personalCodeService.getCurrentUserPersonalCode()).thenReturn(personalCode);
        when(bankAccountRepository.seeYourBalance(personalCode)).thenReturn(Optional.of(bankAccount));

        SeeYourBalanceRequest request = new SeeYourBalanceRequest();
        SeeYourBalanceResponse response = service.execute(request);

        assertNotNull(response);
        assertTrue(response.getBankAccount().isPresent());
        assertEquals(0, response.getBankAccount().get().getBalance());

        verify(personalCodeService, times(1)).getCurrentUserPersonalCode();
        verify(bankAccountRepository, times(1)).seeYourBalance(personalCode);
    }

    @Test
    void shouldReturnEmptyWhenBalanceNotFound() {
        String personalCode = "1234567890";

        when(personalCodeService.getCurrentUserPersonalCode()).thenReturn(personalCode);
        when(bankAccountRepository.seeYourBalance(personalCode)).thenReturn(Optional.empty());

        SeeYourBalanceRequest request = new SeeYourBalanceRequest();
        SeeYourBalanceResponse response = service.execute(request);

        assertNotNull(response);
        assertFalse(response.getBankAccount().isPresent());

        verify(personalCodeService, times(1)).getCurrentUserPersonalCode();
        verify(bankAccountRepository, times(1)).seeYourBalance(personalCode);
    }
}


 */
