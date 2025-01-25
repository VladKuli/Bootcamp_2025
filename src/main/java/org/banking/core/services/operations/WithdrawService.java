package org.banking.core.services.operations;

import org.banking.core.database.JpaBankAccountRepository;
import org.banking.core.request.operations.WithdrawRequest;
import org.banking.core.response.CoreError;
import org.banking.core.response.operations.WithdrawResponse;
import org.banking.core.services.user.GetCurrentUserPersonalCodeService;
import org.banking.core.services.validators.WithdrawValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
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
