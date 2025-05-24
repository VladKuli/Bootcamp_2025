package org.banking.web_ui.controllers.adminControllers.bankAccount_controllers;


import lombok.RequiredArgsConstructor;
import org.banking.core.request.bankAccount.AddBankAccountRequest;
import org.banking.core.response.bankAccount.AddBankAccountResponse;
import org.banking.core.services.bankAccount.AddBankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AddBankAccountController {

    private final AddBankAccountService addBankAccountService;

    @GetMapping(value = "/addBankAccount")
    public String showAddBankAccountPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new AddBankAccountRequest());
        return "addBankAccount";
    }

    @PostMapping("/addBankAccount")
    public String processAddBankAccountRequest(@ModelAttribute(value = "request") AddBankAccountRequest request,
                                               ModelMap modelMap) {
        AddBankAccountResponse response = addBankAccountService.execute(request);
        if (response.getErrors().isEmpty()) {
            return "addBankAccountSuccess";
        } else {
            modelMap.addAttribute("errors", response.getErrors());
            return "addBankAccount";
        }
    }
}
