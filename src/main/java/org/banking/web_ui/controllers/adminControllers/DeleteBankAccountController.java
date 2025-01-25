package org.banking.web_ui.controllers.adminControllers;

import org.banking.core.request.DeleteBankAccountRequest;
import org.banking.core.request.DeleteUserRequest;
import org.banking.core.response.DeleteBankAccountResponse;
import org.banking.core.services.DeleteBankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DeleteBankAccountController {
    @Autowired
    private DeleteBankAccountService service;

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
