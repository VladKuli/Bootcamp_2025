package org.banking.web_ui.controllers.userControllers;

import org.banking.core.database.JpaBankAccountRepository;
import org.banking.core.database.JpaUserRepository;
import org.banking.core.domain.BankAccount;
import org.banking.core.request.operations.SeeYourBalanceRequest;
import org.banking.core.response.operations.SeeYourBalanceResponse;
import org.banking.core.services.operations.SeeYourBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class OutgoingTransactionController {

    @Autowired
    private SeeYourBalanceService service;

    @GetMapping("/seeOutgoingTransactions")
    public String getOutgoingTransactions(ModelMap modelMap) {
        SeeYourBalanceResponse response = service.execute(new SeeYourBalanceRequest());

        if (response.getBankAccount().isPresent()) {
            modelMap.addAttribute("bankAccount", response.getBankAccount().get());
            modelMap.addAttribute("outgoingTransactions", response.getBankAccount().get().getOutgoingTransactions());
        }
        return "seeOutgoingTransactions";
    }
}
