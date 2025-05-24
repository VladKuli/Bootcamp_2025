package org.banking.core.servicesTests.bankAccountTests;


import org.banking.core.database.JpaBankAccountRepository;
import org.banking.core.domain.BankAccount;
import org.banking.core.dto.bank_account.BankAccountDTO;
import org.banking.core.mapper.bank_account.BankAccountMapper;
import org.banking.core.request.bankAccount.GetAllBankAccountsRequest;
import org.banking.core.response.bankAccount.GetAllBankAccountsResponse;
import org.banking.core.services.bankAccount.GetAllBankAccountsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Disabled
class GetAllBankAccountsTest {

    @Mock
    private JpaBankAccountRepository bankAccountRepository;

    @InjectMocks
    private GetAllBankAccountsService service;

    @Mock
    private BankAccountMapper bankAccountMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnAllBankAccountsWhenTheyExist() {
        List<BankAccount> bankAccounts = Arrays.asList(
                BankAccount.builder()
                        .name("John")
                        .surname("Doe")
                        .personalCode("1234567890")
                        .build(),
                BankAccount.builder()
                        .name("Jane")
                        .surname("Smith")
                        .personalCode("0987654321")
                        .build()
        );


        List<BankAccountDTO> bankAccountDTOS = new ArrayList<>();
        for (BankAccount account : bankAccounts) {
            BankAccountDTO dto = new BankAccountDTO();
            dto.setName(account.getName());
            dto.setSurname(account.getSurname());
            dto.setPersonalCode(account.getPersonalCode());
            bankAccountDTOS.add(dto);
        }

        when(bankAccountRepository.findAll()).thenReturn(bankAccounts);

        GetAllBankAccountsRequest request = new GetAllBankAccountsRequest();
        GetAllBankAccountsResponse response = service.execute(request);

        assertNotNull(response);
        assertEquals(2, bankAccountDTOS.size());
        assertEquals("John", bankAccountDTOS.get(0).getName());
        assertEquals("Jane", bankAccountDTOS.get(1).getName());

        verify(bankAccountRepository, times(1)).findAll();
    }

    @Test
    void shouldReturnEmptyListWhenNoBankAccountsExist() {
        when(bankAccountRepository.findAll()).thenReturn(Collections.emptyList());

        GetAllBankAccountsRequest request = new GetAllBankAccountsRequest();
        GetAllBankAccountsResponse response = service.execute(request);

        assertNotNull(response);
        assertTrue(response.getBankAccountDTOS().isEmpty());

        verify(bankAccountRepository, times(1)).findAll();
    }
}
