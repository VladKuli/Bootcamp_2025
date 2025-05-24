package org.banking.core.servicesTests.userTests;

import org.banking.core.database.JpaBankAccountRepository;
import org.banking.core.database.JpaUserRepository;
import org.banking.core.request.user.DeleteUserRequest;
import org.banking.core.response.CoreError;
import org.banking.core.response.user.DeleteUserResponse;
import org.banking.core.services.user.DeleteUserService;
import org.banking.core.services.validators.userValidators.DeleteUserValidator;
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
@Disabled
@ExtendWith(MockitoExtension.class)
class DeleteUserServiceTest {

    @Mock
    private JpaBankAccountRepository bankAccountRepository;

    @Mock
    private JpaUserRepository userRepository;

    @Mock
    private DeleteUserValidator validator;

    @InjectMocks
    private DeleteUserService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldDeleteUserWhenRequestIsValid() {
        DeleteUserRequest request = new DeleteUserRequest("1234567890");
        List<CoreError> noErrors = Collections.emptyList();

        when(validator.validate(request)).thenReturn(noErrors);

        DeleteUserResponse response = service.execute(request);

        assertNotNull(response);
        assertTrue(response.isDeleted());

        verify(validator, times(1)).validate(request);
        verify(userRepository, times(1)).deleteByPersonalCode("1234567890");
        verify(bankAccountRepository, times(1)).deleteByPersonalCode("1234567890");
    }

    @Test
    void shouldReturnErrorsWhenRequestIsInvalid() {
        DeleteUserRequest request = new DeleteUserRequest("");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Personal code must not be empty"));

        when(validator.validate(request)).thenReturn(errors);

        DeleteUserResponse response = service.execute(request);

        assertNotNull(response);
        assertFalse(response.isDeleted());
        assertNotNull(response.getErrors());
        assertEquals(1, response.getErrors().size());
        assertEquals("Personal code must not be empty", response.getErrors().get(0).getMessage());

        verify(validator, times(1)).validate(request);
        verifyNoInteractions(userRepository);
        verifyNoInteractions(bankAccountRepository);
    }
}
