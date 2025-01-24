package org.banking.web_ui.controllers.adminControllers;

import org.banking.core.request.GetAllBankAccountsRequest;
import org.banking.core.response.GetAllBankAccountsResponse;
import org.banking.core.services.GetAllBankAccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GetAllBankAccountsController {

    @Autowired
    private GetAllBankAccountsService service;

    @GetMapping(value = "/getAllBankAccounts")
    public String showAllBooks(ModelMap modelMap) {
        GetAllBankAccountsResponse response = service.execute(
                new GetAllBankAccountsRequest()
        );
        modelMap.addAttribute("bank_accounts", response.getBankAccountList());
        return "/getAllBankAccounts";
    }

}
