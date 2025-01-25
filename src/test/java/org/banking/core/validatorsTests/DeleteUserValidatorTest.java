package org.banking.core.validatorsTests;

import org.banking.core.request.user.DeleteUserRequest;
import org.banking.core.response.CoreError;
import org.banking.core.services.validators.DeleteUserValidator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DeleteUserValidatorTest {

    private final DeleteUserValidator validator = new DeleteUserValidator();

    @Test
    void shouldReturnNoErrorsForValidPersonalCode() {
        DeleteUserRequest request = new DeleteUserRequest("1234567890");

        List<CoreError> errors = validator.validate(request);

        assertTrue(errors.isEmpty());
    }

    @Test
    void shouldReturnErrorWhenPersonalCodeIsNull() {
        DeleteUserRequest request = new DeleteUserRequest(null);

        List<CoreError> errors = validator.validate(request);

        assertEquals(1, errors.size());
        assertEquals("Personal code field must be filled", errors.get(0).getMessage());
    }

    @Test
    void shouldReturnErrorWhenPersonalCodeIsBlank() {
        DeleteUserRequest request = new DeleteUserRequest("   ");

        List<CoreError> errors = validator.validate(request);

        assertEquals(1, errors.size());
        assertEquals("Personal code field must be filled", errors.get(0).getMessage());
    }

    @Test
    void shouldReturnErrorWhenPersonalCodeIsEmpty() {
        DeleteUserRequest request = new DeleteUserRequest("");

        List<CoreError> errors = validator.validate(request);

        assertEquals(1, errors.size());
        assertEquals("Personal code field must be filled", errors.get(0).getMessage());
    }
}

