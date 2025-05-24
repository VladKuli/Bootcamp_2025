package org.banking.web_ui.controllers.adminControllers.bankAccount_controllers;

import lombok.RequiredArgsConstructor;
import org.banking.core.request.bankAccount.DeleteBankAccountRequest;
import org.banking.core.request.user.DeleteUserRequest;
import org.banking.core.response.bankAccount.DeleteBankAccountResponse;
import org.banking.core.services.bankAccount.DeleteBankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class DeleteBankAccountController {

    private final DeleteBankAccountService service;

    @GetMapping(value = "/deleteBankAccount")
    public String showDeleteBankAccountPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new DeleteUserRequest());
        return "deleteBankAccount";
    }

    @PostMapping("/deleteBankAccount")
    public String processDeleteBankAccountRequest(@ModelAttribute(value = "request") DeleteBankAccountRequest request,
                                                  ModelMap modelMap) {
        DeleteBankAccountResponse response = service.execute(request);
        if (response.getErrorList().isEmpty()) {
            return "deleteBankAccountSuccess";
        } else {
            modelMap.addAttribute("errors", response.getErrors());
            return "deleteBankAccount";
        }
    }


}
