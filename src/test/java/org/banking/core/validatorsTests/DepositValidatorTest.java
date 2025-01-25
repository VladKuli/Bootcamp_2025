package org.banking.core.validatorsTests;

import org.banking.core.request.operations.DepositRequest;
import org.banking.core.response.CoreError;
import org.banking.core.services.validators.DepositValidator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DepositValidatorTest {

    private final DepositValidator validator = new DepositValidator();

    @Test
    void shouldReturnNoErrorsForValidAmount() {
        DepositRequest request = new DepositRequest(100);

        List<CoreError> errors = validator.validate(request);

        assertTrue(errors.isEmpty());
    }

    @Test
    void shouldReturnErrorForZeroAmount() {
        DepositRequest request = new DepositRequest(0);

        List<CoreError> errors = validator.validate(request);

        assertEquals(1, errors.size());
        assertEquals("Amount must be positive.", errors.get(0).getMessage());
    }

    @Test
    void shouldReturnErrorForNegativeAmount() {
        DepositRequest request = new DepositRequest(-50);

        List<CoreError> errors = validator.validate(request);

        assertEquals(1, errors.size());
        assertEquals("Amount must be positive.", errors.get(0).getMessage());
    }
}

