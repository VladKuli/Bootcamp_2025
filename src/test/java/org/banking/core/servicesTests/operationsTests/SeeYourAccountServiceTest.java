package org.banking.core.servicesTests.operationsTests;
/*

import org.banking.core.database.JpaBankAccountRepository;
import org.banking.core.domain.BankAccount;
import org.banking.core.domain.IBAN;
import org.banking.core.request.operations.SeeYourBalanceRequest;
import org.banking.core.response.operations.SeeYourBalanceResponse;
import org.banking.core.services.bankAccount.GetCurrentBankAccountService;
import org.banking.core.services.operations.SeeYourBalanceService;
import org.banking.core.services.user.GetCurrentUserPersonalCodeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SeeYourAccountServiceTest {

    @Mock
    private GetCurrentBankAccountService getCurrentBankAccountService;

    @InjectMocks
    private SeeYourBalanceService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnBalanceSuccessfully() {
        BankAccount bankAccount = BankAccount.builder()
                .id(0L)
                .name("Example")
                .surname("Example2")
                .personalCode("123456")
                .build();

        IBAN iban = IBAN.builder()
                .id(0L)
                .ibanNumber("LV-Example")
                .balance(0)
                .build();
        bankAccount.setIBAN(List.of(iban));

        when(getCurrentBankAccountService.get()).thenReturn(Optional.of(bankAccount));

        SeeYourBalanceRequest request = new SeeYourBalanceRequest();
        SeeYourBalanceResponse response = service.execute(request);

        assertNotNull(response);
        assertTrue(response.getBankAccount().isPresent());
        verify(getCurrentBankAccountService, times(1)).get();
    }
}


 */