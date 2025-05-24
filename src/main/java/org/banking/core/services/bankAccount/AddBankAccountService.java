package org.banking.core.services.bankAccount;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.banking.core.database.JpaBankAccountRepository;
import org.banking.core.domain.BankAccount;
import org.banking.core.domain.IBAN;
import org.banking.core.dto.bank_account.BankAccountDTO;
import org.banking.core.mapper.bank_account.BankAccountMapper;
import org.banking.core.request.bankAccount.AddBankAccountRequest;
import org.banking.core.response.bankAccount.AddBankAccountResponse;
import org.banking.core.response.CoreError;
import org.banking.core.services.operations.IBANGeneratorService;
import org.banking.core.services.validators.bankAccountValidators.AddBankAccountValidator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AddBankAccountService {


    private final JpaBankAccountRepository bankAccountRepository;

    private final AddBankAccountValidator validator;

    private final IBANGeneratorService ibanGeneratorService;

    private final BankAccountMapper bankAccountMapper;

    public AddBankAccountResponse execute(AddBankAccountRequest request) {
        log.info("Received request to add a new bank account: {}", request);

        List<CoreError> errorList = validator.validate(request);
        if (!errorList.isEmpty()) {

            log.warn("Validation failed for request: {}. Errors: {}", request, errorList);

            return new AddBankAccountResponse(errorList);
        } else {

            Optional<BankAccount> bankAccount = buildingBankAccount(request);

            bankAccount.ifPresent(bankAccountRepository::save);

            log.info("Successfully added bank account: {}", bankAccount);

            BankAccountDTO bankAccountDto = bankAccountMapper.toDto(bankAccount.get());
            return new AddBankAccountResponse(bankAccountDto);
        }
    }


    private Optional<BankAccount> buildingBankAccount(AddBankAccountRequest request) {
        BankAccount bankAccount = BankAccount.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .personalCode(request.getPersonalCode())
                .build();

        List<IBAN> iban = ibanGeneratorService.generateIBAN(bankAccount);
        bankAccount.setIBAN(iban);

        return Optional.of(bankAccount);
    }
}
