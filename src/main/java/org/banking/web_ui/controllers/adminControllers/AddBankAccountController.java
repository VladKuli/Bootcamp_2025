package org.banking.web_ui.controllers.adminControllers;


import org.banking.core.request.AddBankAccountRequest;
import org.banking.core.response.AddBankAccountResponse;
import org.banking.core.services.AddBankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

//TODO REWRITE LOGIC OF RETURNING WEB PAGE
@Controller
public class AddBankAccountController {

    @Autowired
    private AddBankAccountService addBankAccountService;

    @GetMapping(value = "/addBankAccount")
    public String showAddBankAccountPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new AddBankAccountRequest());
        return "addBankAccount";
    }

    @PostMapping("/addBankAccount")
    public String processAddBankAccountRequest(@ModelAttribute(value = "request") AddBankAccountRequest request,
                                               ModelMap modelMap) {
        AddBankAccountResponse response = addBankAccountService.execute(request);
        if (!response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "addBankAccount";
        } else {
            return "addBankAccountSuccess";
        }
    }
}
