package org.banking.core.services;

import org.banking.core.database.JpaBankAccountRepository;
import org.banking.core.database.JpaUserRepository;
import org.banking.core.domain.BankAccount;
import org.banking.core.request.SeeYourBalanceRequest;
import org.banking.core.response.CoreError;
import org.banking.core.response.SeeYourBalanceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class SeeYourBalanceService {

    @Autowired
    private JpaBankAccountRepository bankAccountRepository;

    public SeeYourBalanceResponse execute(SeeYourBalanceRequest request) {
        String personalCode = getCurrentUserPersonalCode();

        Optional<BankAccount> bankAccount = bankAccountRepository.seeYourBalance(personalCode);

        return new SeeYourBalanceResponse(bankAccount);
    }


    private String getCurrentUserPersonalCode() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            return principal.toString();
        }
    }
}
