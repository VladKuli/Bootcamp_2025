package org.banking.core.servicesTests.userTests;

import org.banking.core.database.JpaUserRepository;
import org.banking.core.domain.User;
import org.banking.core.request.user.AddUserRequest;
import org.banking.core.response.user.AddUserResponse;
import org.banking.core.response.CoreError;
import org.banking.core.services.user.AddUserService;
import org.banking.core.services.validators.userValidators.AddUserValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@Disabled

    @ExtendWith(MockitoExtension.class)
    public class AddUserServiceTest {

        @Mock
        private JpaUserRepository userRepository;

        @Mock
        private AddUserValidator validator;

        @Mock
        private PasswordEncoder passwordEncoder;

        @InjectMocks
        private AddUserService service;

        @BeforeEach
        void setUp() {
            MockitoAnnotations.openMocks(this);
        }

        @Test
        void shouldAddUserWhenRequestIsValid() {
            AddUserRequest request = new AddUserRequest("1234567890", "password123", "ADMIN");
            List<CoreError> noErrors = Collections.emptyList();

            when(validator.validate(request)).thenReturn(noErrors);
            when(passwordEncoder.encode(request.getPassword())).thenReturn("encryptedPassword");

            User savedUser = User.builder()
                    .personalCode("1234567890")
                    .password("encryptedPassword")
                    .role("ADMIN").build();
            when(userRepository.save(any(User.class))).thenReturn(savedUser);

            AddUserResponse response = service.execute(request);

            assertNotNull(response);
            assertNotNull(response.getUser());
            assertEquals("1234567890", response.getUser().getPersonalCode());
            assertEquals("encryptedPassword", response.getUser().getPassword());
            assertEquals("ADMIN", response.getUser().getRole());

            verify(validator, times(1)).validate(request);
            verify(passwordEncoder, times(1)).encode(request.getPassword());
            verify(userRepository, times(1)).save(any(User.class));
        }

        @Test
        void shouldReturnErrorsWhenRequestIsInvalid() {
            AddUserRequest request = new AddUserRequest("", "", "");
            List<CoreError> errors = new ArrayList<>();
            errors.add(new CoreError("Personal code field must be filled"));
            errors.add(new CoreError("Password field must be filled"));
            errors.add(new CoreError("Role field must be filled"));

            when(validator.validate(request)).thenReturn(errors);

            AddUserResponse response = service.execute(request);

            assertNotNull(response);
            assertNull(response.getUser());
            assertEquals(3, response.getErrors().size());
            assertEquals("Personal code field must be filled", response.getErrors().get(0).getMessage());
            assertEquals("Password field must be filled", response.getErrors().get(1).getMessage());
            assertEquals("Role field must be filled", response.getErrors().get(2).getMessage());

            verify(validator, times(1)).validate(request);
            verifyNoInteractions(passwordEncoder);
            verifyNoInteractions(userRepository);
        }

}
