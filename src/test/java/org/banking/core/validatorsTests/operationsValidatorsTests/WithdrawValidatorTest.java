package org.banking.core.validatorsTests.operationsValidatorsTests;


import org.banking.core.request.operations.WithdrawRequest;
import org.banking.core.response.CoreError;
import org.banking.core.services.validators.operationsValidators.WithdrawValidator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WithdrawValidatorTest {

    private final WithdrawValidator validator = new WithdrawValidator();

    @Test
    void shouldReturnNoErrorsForValidAmount() {
        WithdrawRequest request = new WithdrawRequest(100);

        List<CoreError> errors = validator.validate(request);

        assertTrue(errors.isEmpty());
    }

    @Test
    void shouldReturnErrorForZeroAmount() {
        WithdrawRequest request = new WithdrawRequest(0);

        List<CoreError> errors = validator.validate(request);

        assertEquals(1, errors.size());
        assertEquals("Amount must be positive.", errors.get(0).getMessage());
    }

    @Test
    void shouldReturnErrorForNegativeAmount() {
        WithdrawRequest request = new WithdrawRequest(-50);

        List<CoreError> errors = validator.validate(request);

        assertEquals(1, errors.size());
        assertEquals("Amount must be positive.", errors.get(0).getMessage());
    }
}

