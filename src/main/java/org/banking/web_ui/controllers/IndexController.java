package org.banking.web_ui.controllers;

import org.banking.core.domain.BankAccount;
import org.banking.core.services.bankAccount.GetCurrentBankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class IndexController {


    @Autowired
    private GetCurrentBankAccountService getCurrentBankAccountService;

    @GetMapping(value = "/admin")
    public String index() {
        return "indexAdmin";
    }

    @GetMapping(value = "/user")
    public String user(ModelMap modelMap) {
        Optional<BankAccount> bankAccount = getCurrentBankAccountService.get();

        bankAccount.ifPresent(account -> modelMap.addAttribute("bankAccount", account));
        return "indexUser";
    }
}

