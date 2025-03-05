package org.banking.web_ui.controllers.userControllers;

import org.banking.core.domain.BankAccount;
import org.banking.core.request.operations.SeeYourBalanceRequest;
import org.banking.core.response.bankAccount.GetCurrentBankAccountResponse;
import org.banking.core.response.operations.SeeYourBalanceResponse;
import org.banking.core.services.bankAccount.GetCurrentBankAccountService;
import org.banking.core.services.operations.SeeYourBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class SeeYourBalanceController {

    @Autowired
    private GetCurrentBankAccountService getCurrentBankAccount;

    @GetMapping(value = "/seeYourBalance")
    public String showSeeYourBalancePage(ModelMap modelMap) {
        Optional<BankAccount> bankAccount = getCurrentBankAccount.get();
        bankAccount.ifPresent(account -> modelMap.addAttribute("bankAccount", account));
        return "seeYourBalance";
    }
}