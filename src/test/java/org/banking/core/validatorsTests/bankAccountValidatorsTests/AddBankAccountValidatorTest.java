package org.banking.core.validatorsTests.bankAccountValidatorsTests;

import org.banking.core.request.bankAccount.AddBankAccountRequest;
import org.banking.core.response.CoreError;
import org.banking.core.services.validators.bankAccountValidators.AddBankAccountValidator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

    class AddBankAccountValidatorTest {

        private final AddBankAccountValidator validator = new AddBankAccountValidator();

        @Test
        void shouldReturnNoErrorsForValidRequest() {
            AddBankAccountRequest request = new AddBankAccountRequest("John", "Doe", "1234567890");

            List<CoreError> errors = validator.validate(request);

            assertTrue(errors.isEmpty());
        }

        @Test
        void shouldReturnErrorWhenNameIsEmpty() {
            AddBankAccountRequest request = new AddBankAccountRequest("", "Doe", "1234567890");

            List<CoreError> errors = validator.validate(request);

            assertEquals(1, errors.size());
            assertEquals("Name field must be filled", errors.get(0).getMessage());
        }

        @Test
        void shouldReturnErrorWhenSurnameIsEmpty() {
            AddBankAccountRequest request = new AddBankAccountRequest("John", "", "1234567890");

            List<CoreError> errors = validator.validate(request);

            assertEquals(1, errors.size());
            assertEquals("Surname field must be filled", errors.get(0).getMessage());
        }

        @Test
        void shouldReturnErrorWhenPersonalCodeIsEmpty() {
            AddBankAccountRequest request = new AddBankAccountRequest("John", "Doe", "");

            List<CoreError> errors = validator.validate(request);

            assertEquals(1, errors.size());
            assertEquals("Personal code field must be filled", errors.get(0).getMessage());
        }

        @Test
        void shouldReturnAllErrorsForInvalidRequest() {
            AddBankAccountRequest request = new AddBankAccountRequest("", "", "");

            List<CoreError> errors = validator.validate(request);

            assertEquals(3, errors.size());
            assertEquals("Name field must be filled", errors.get(0).getMessage());
            assertEquals("Surname field must be filled", errors.get(1).getMessage());
            assertEquals("Personal code field must be filled", errors.get(2).getMessage());
        }

}
