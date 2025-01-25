package org.banking.web_ui.controllers.userControllers;

import org.banking.core.request.DeleteUserRequest;
import org.banking.core.request.MoneyTransferRequest;
import org.banking.core.response.DeleteUserResponse;
import org.banking.core.response.MoneyTransferResponse;
import org.banking.core.services.DeleteUserService;
import org.banking.core.services.MoneyTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MoneyTransferController {

    @Autowired
    private MoneyTransferService service;

    @GetMapping(value = "/moneyTransfer")
    public String showMoneyTransferPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new MoneyTransferRequest());
        return "moneyTransfer";
    }

    @PostMapping(value = "/moneyTransfer")
    public String processDeleteUserRequest(@ModelAttribute(value = "request")MoneyTransferRequest request,
                                           ModelMap modelMap) {
        MoneyTransferResponse responses = service.execute(request);
        if (responses.isCompleted()) {
            return "moneyTransferSuccess";
        } else {
            modelMap.addAttribute("errors", responses.getErrors());
            return "moneyTransfer";
        }
    }
}
