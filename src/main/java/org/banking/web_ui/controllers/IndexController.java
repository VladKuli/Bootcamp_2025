package org.banking.web_ui.controllers;

import org.banking.core.domain.BankAccount;
import org.banking.core.domain.Card;
import org.banking.core.domain.IBAN;
import org.banking.core.services.bankAccount.GetCurrentBankAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class IndexController {


    @Autowired
    private GetCurrentBankAccountService getCurrentBankAccountService;

    private static Logger logger = LoggerFactory.getLogger(IndexController.class);

    @GetMapping(value = "/admin")
    public String index() {
        return "indexAdmin";
    }

    @GetMapping(value = "/user")
    public String user(ModelMap modelMap) {
        Optional<BankAccount> bankAccount = getCurrentBankAccountService.get();
        if (bankAccount.isPresent()) {
            List<IBAN> iban = bankAccount.get().getIBAN();
            logger.info("getting IBAN {}", iban);
            modelMap.addAttribute("bankAccount", bankAccount.get());
            modelMap.addAttribute("iban", iban);
        } else {
            modelMap.addAttribute("iban", List.of());
        }

        return "indexUser";
    }

}

