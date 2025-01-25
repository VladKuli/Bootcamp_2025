package org.banking.core.services;

import org.banking.core.database.JpaBankAccountRepository;
import org.banking.core.domain.BankAccount;
import org.banking.core.request.WithdrawRequest;
import org.banking.core.response.CoreError;
import org.banking.core.response.WithdrawResponse;
import org.banking.core.services.validators.WithdrawValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@Transactional
@Component
public class WithdrawService {

    @Autowired
    private JpaBankAccountRepository bankAccountRepository;

    @Autowired
    private GetCurrentUserPersonalCodeService personalCodeService;

    @Autowired
    private WithdrawValidator validator;

    public WithdrawResponse execute(WithdrawRequest request) {
        List<CoreError> errorList = validator.validate(request);

        if (errorList.isEmpty()) {

            String personalCode = personalCodeService.getCurrentUserPersonalCode();
            bankAccountRepository.withdraw(personalCode,request.getAmount());

            return new WithdrawResponse(true);
        } else {
            return new WithdrawResponse(errorList);
        }
    }


 }
