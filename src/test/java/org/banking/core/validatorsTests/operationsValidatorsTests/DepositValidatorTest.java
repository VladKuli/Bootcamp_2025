package org.banking.core.validatorsTests.operationsValidatorsTests;
/*
import org.banking.core.request.operations.DepositRequest;
import org.banking.core.response.CoreError;
import org.banking.core.services.validators.operationsValidators.DepositValidator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DepositValidatorTest {

    private final DepositValidator validator = new DepositValidator();

    @Test
    void shouldReturnNoErrorsForValidAmount() {
        DepositRequest request = DepositRequest.builder()
                .personalCode("12345789")
                .amount(100)
                .build();

        List<CoreError> errors = validator.validate(request);

        assertTrue(errors.isEmpty());
    }

    @Test
    void shouldReturnErrorForZeroAmount() {
        DepositRequest request = DepositRequest.builder()
                .personalCode("123456789")
                .amount(0)
                .build();

        List<CoreError> errors = validator.validate(request);

        assertEquals(1, errors.size());
        assertEquals("Amount must be positive.", errors.get(0).getMessage());
    }

    @Test
    void shouldReturnErrorForNegativeAmount() {
        DepositRequest request = DepositRequest.builder()
                .personalCode("12345678")
                .amount(-100)
                .build();

        List<CoreError> errors = validator.validate(request);

        assertEquals(1, errors.size());
        assertEquals("Amount must be positive.", errors.get(0).getMessage());
    }
}

 */

