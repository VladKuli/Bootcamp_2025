package org.banking.web_ui.controllers.userControllers;

import org.banking.core.domain.BankAccount;
import org.banking.core.services.bankAccount.GetCurrentBankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class IncomingTransactionController {

    @Autowired
    private GetCurrentBankAccountService getCurrentBankAccountService;

    @GetMapping("/seeIncomingTransactions")
    public String seeIncomingTransactions(ModelMap modelMap) {

        Optional<BankAccount> bankAccount = getCurrentBankAccountService.get();

        if (bankAccount.isPresent()) {
            modelMap.addAttribute("bankAccount", bankAccount.get());
            modelMap.addAttribute("incomingTransactions",bankAccount.get().getIncomingTransactions());

        }
        return "seeIncomingTransactions";
    }
}
