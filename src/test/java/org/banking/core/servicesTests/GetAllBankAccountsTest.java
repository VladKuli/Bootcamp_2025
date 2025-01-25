package org.banking.core.servicesTests;


import org.banking.core.database.JpaBankAccountRepository;
import org.banking.core.domain.BankAccount;
import org.banking.core.request.bankAccount.GetAllBankAccountsRequest;
import org.banking.core.response.bankAccount.GetAllBankAccountsResponse;
import org.banking.core.services.bankAccount.GetAllBankAccountsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetAllBankAccountsServiceTest {

    @Mock
    private JpaBankAccountRepository bankAccountRepository;

    @InjectMocks
    private GetAllBankAccountsService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnAllBankAccountsWhenTheyExist() {
        List<BankAccount> bankAccounts = Arrays.asList(
                new BankAccount("John", "Doe", "1234567890"),
                new BankAccount("Jane", "Smith", "0987654321")
        );

        when(bankAccountRepository.findAll()).thenReturn(bankAccounts);

        GetAllBankAccountsRequest request = new GetAllBankAccountsRequest();
        GetAllBankAccountsResponse response = service.execute(request);

        assertNotNull(response);
        assertEquals(2, response.getBankAccountList().size());
        assertEquals("John", response.getBankAccountList().get(0).getName());
        assertEquals("Jane", response.getBankAccountList().get(1).getName());

        verify(bankAccountRepository, times(1)).findAll();
    }

    @Test
    void shouldReturnEmptyListWhenNoBankAccountsExist() {
        when(bankAccountRepository.findAll()).thenReturn(Collections.emptyList());

        GetAllBankAccountsRequest request = new GetAllBankAccountsRequest();
        GetAllBankAccountsResponse response = service.execute(request);

        assertNotNull(response);
        assertTrue(response.getBankAccountList().isEmpty());

        verify(bankAccountRepository, times(1)).findAll();
    }
}

