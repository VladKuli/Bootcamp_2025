package org.banking.web_ui.controllers.adminControllers.bankAccount_controllers;

import lombok.RequiredArgsConstructor;
import org.banking.core.request.bankAccount.SearchBankAccountRequest;
import org.banking.core.response.bankAccount.SearchBankAccountResponse;
import org.banking.core.services.bankAccount.SearchBankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class SearchBankAccountController {

    private final SearchBankAccountService service;

    @GetMapping(value = "/searchBankAccount")
    public String showSearchBankAccountPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new SearchBankAccountRequest());
        return "searchBankAccount";
    }

    @PostMapping("/searchBankAccount")
    public String processSearchBankAccountRequest(@ModelAttribute(value = "request") SearchBankAccountRequest request,
                                                  ModelMap modelMap) {
        SearchBankAccountResponse response = service.execute(request);
        if (response.getErrors().isEmpty()) {

            modelMap.addAttribute("bank_accounts", response.getBankAccountList());
            return "searchBankAccountSuccess";
        } else {
            modelMap.addAttribute("errors", response.getErrors());
            return "searchBankAccount";
        }
    }
}
