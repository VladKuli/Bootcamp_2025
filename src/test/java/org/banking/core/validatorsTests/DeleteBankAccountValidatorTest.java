package org.banking.core.validatorsTests;

import org.banking.core.request.bankAccount.DeleteBankAccountRequest;
import org.banking.core.response.CoreError;
import org.banking.core.services.validators.DeleteBankAccountValidator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DeleteBankAccountValidatorTest {

    private final DeleteBankAccountValidator validator = new DeleteBankAccountValidator();

    @Test
    void shouldReturnNoErrorsForValidPersonalCode() {
        DeleteBankAccountRequest request = new DeleteBankAccountRequest("1234567890");

        List<CoreError> errors = validator.validate(request);

        assertTrue(errors.isEmpty());
    }

    @Test
    void shouldReturnErrorWhenPersonalCodeIsNull() {
        DeleteBankAccountRequest request = new DeleteBankAccountRequest(null);

        List<CoreError> errors = validator.validate(request);

        assertEquals(1, errors.size());
        assertEquals("Personal code field must be filled", errors.get(0).getMessage());
    }

    @Test
    void shouldReturnErrorWhenPersonalCodeIsBlank() {
        DeleteBankAccountRequest request = new DeleteBankAccountRequest("   ");

        List<CoreError> errors = validator.validate(request);

        assertEquals(1, errors.size());
        assertEquals("Personal code field must be filled", errors.get(0).getMessage());
    }

    @Test
    void shouldReturnErrorWhenPersonalCodeIsEmpty() {
        DeleteBankAccountRequest request = new DeleteBankAccountRequest("");

        List<CoreError> errors = validator.validate(request);

        assertEquals(1, errors.size());
        assertEquals("Personal code field must be filled", errors.get(0).getMessage());
    }
}

