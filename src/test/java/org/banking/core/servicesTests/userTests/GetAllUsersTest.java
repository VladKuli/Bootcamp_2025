package org.banking.core.servicesTests.userTests;
/*
import org.banking.core.database.JpaUserRepository;
import org.banking.core.domain.User;
import org.banking.core.request.user.GetAllUsersRequest;
import org.banking.core.response.user.GetAllUsersResponse;
import org.banking.core.services.user.GetAllUsersService;
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
class GetAllUsersTest {

    @Mock
    private JpaUserRepository userRepository;

    @InjectMocks
    private GetAllUsersService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnAllUsersWithEncryptedPasswords() {

        List<User> users = Arrays.asList(
                User.builder()
                        .personalCode("1234567890")
                        .password("password123")
                        .role("ADMIN")
                        .build(),
                User.builder()
                        .personalCode("0987654321")
                        .password("securepass")
                        .role("USER")
                        .build()
        );

        when(userRepository.findAll()).thenReturn(users);

        GetAllUsersRequest request = new GetAllUsersRequest();
        GetAllUsersResponse response = service.execute(request);

        assertNotNull(response);
        assertEquals(2, response.getUsers().size());
        assertEquals("1234567890", response.getUsers().get(0).getPersonalCode());
        assertEquals("[ENCRYPTED]", response.getUsers().get(0).getPassword());
        assertEquals("ADMIN", response.getUsers().get(0).getRole());
        assertEquals("0987654321", response.getUsers().get(1).getPersonalCode());
        assertEquals("[ENCRYPTED]", response.getUsers().get(1).getPassword());
        assertEquals("USER", response.getUsers().get(1).getRole());

        verify(userRepository, times(1)).findAll();
    }

    @Test
    void shouldReturnEmptyListWhenNoUsersExist() {
        when(userRepository.findAll()).thenReturn(Collections.emptyList());

        GetAllUsersRequest request = new GetAllUsersRequest();
        GetAllUsersResponse response = service.execute(request);

        assertNotNull(response);
        assertTrue(response.getUsers().isEmpty());

        verify(userRepository, times(1)).findAll();
    }
}

 */

