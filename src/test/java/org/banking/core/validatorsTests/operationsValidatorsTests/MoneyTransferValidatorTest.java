package org.banking.core.validatorsTests.operationsValidatorsTests;
/*

import org.banking.core.request.operations.MoneyTransferRequest;
import org.banking.core.response.CoreError;
import org.banking.core.services.validators.operationsValidators.MoneyTransferValidator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MoneyTransferValidatorTest {

    private final MoneyTransferValidator validator = new MoneyTransferValidator();

    @Test
    public void shouldReturnNoErrorsForValidRequest() {
        MoneyTransferRequest request = new MoneyTransferRequest("1234567890", 100);

        List<CoreError> errorList = validator.execute(request);
        assertTrue(errorList.isEmpty());

    }


    @Test
    public void shouldReturnErrorWhenPersonalCodeIsEmpty() {
        MoneyTransferRequest request = new MoneyTransferRequest("", 100);

        List<CoreError> errorList = validator.execute(request);
        assertEquals(1, errorList.size());
        assertEquals("Personal code field must be filled.", errorList.get(0).getMessage());
    }


    @Test
    public void shouldReturnErrorWhenAmountIsEmpty() {
        MoneyTransferRequest request = new MoneyTransferRequest("1234567890", 0);

        List<CoreError> errorList = validator.execute(request);
        assertEquals("Amount must be positive.", errorList.get(0).getMessage());
    }

}


 */