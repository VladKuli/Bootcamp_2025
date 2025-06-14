package org.banking.web_ui.controllers.adminControllers.bankAccount_controllers;

import lombok.RequiredArgsConstructor;
import org.banking.core.dto.iban.IbanDTO;
import org.banking.core.request.bankAccount.GetAllBankAccountsRequest;
import org.banking.core.response.bankAccount.GetAllBankAccountsResponse;
import org.banking.core.services.bankAccount.GetAllBankAccountsService;
import org.banking.core.services.iban.CurrentUserIbanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class GetAllBankAccountsController {

    private final GetAllBankAccountsService service;
    private final CurrentUserIbanService ibanService;

    @GetMapping(value = "/getAllBankAccounts")
    public String showAllBankAccounts(ModelMap modelMap) {
        GetAllBankAccountsResponse response = service.execute(new GetAllBankAccountsRequest());

        modelMap.addAttribute("bank_accounts", response.getBankAccountDTOS());

        int totalBalance = ibanService.getIbanDTO().stream()
                .mapToInt(IbanDTO::getBalance)
                .sum();

        modelMap.addAttribute("balance", totalBalance);
        return "getAllBankAccounts";
    }

}
