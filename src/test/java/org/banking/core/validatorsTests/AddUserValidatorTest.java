package org.banking.core.validatorsTests;

import org.banking.core.request.user.AddUserRequest;
import org.banking.core.response.CoreError;
import org.banking.core.services.validators.AddUserValidator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddUserValidatorTest {

    private final AddUserValidator validator = new AddUserValidator();

    @Test
    void shouldReturnNoErrorsForValidRequest() {
        AddUserRequest request = new AddUserRequest("1234567890", "password123", "ADMIN");

        List<CoreError> errors = validator.validate(request);

        assertTrue(errors.isEmpty());
    }

    @Test
    void shouldReturnErrorWhenPersonalCodeIsEmpty() {
        AddUserRequest request = new AddUserRequest("", "password123", "ADMIN");

        List<CoreError> errors = validator.validate(request);

        assertEquals(1, errors.size());
        assertEquals("Personal code field must be filled", errors.get(0).getMessage());
    }

    @Test
    void shouldReturnErrorWhenPasswordIsEmpty() {
        AddUserRequest request = new AddUserRequest("1234567890", "", "ADMIN");

        List<CoreError> errors = validator.validate(request);

        assertEquals(1, errors.size());
        assertEquals("Password field must be filled", errors.get(0).getMessage());
    }

    @Test
    void shouldReturnErrorWhenRoleIsEmpty() {
        AddUserRequest request = new AddUserRequest("1234567890", "password123", "");

        List<CoreError> errors = validator.validate(request);

        assertEquals(1, errors.size());
        assertEquals("Role field must be filled", errors.get(0).getMessage());
    }

    @Test
    void shouldReturnAllErrorsForInvalidRequest() {
        AddUserRequest request = new AddUserRequest("", "", "");

        List<CoreError> errors = validator.validate(request);

        assertEquals(3, errors.size());
        assertEquals("Personal code field must be filled", errors.get(0).getMessage());
        assertEquals("Password field must be filled", errors.get(1).getMessage());
        assertEquals("Role field must be filled", errors.get(2).getMessage());
    }
}
