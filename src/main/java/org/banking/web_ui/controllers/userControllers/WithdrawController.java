package org.banking.web_ui.controllers.userControllers;

import org.banking.core.request.DepositRequest;
import org.banking.core.request.MoneyTransferRequest;
import org.banking.core.request.WithdrawRequest;
import org.banking.core.response.DepositResponse;
import org.banking.core.response.MoneyTransferResponse;
import org.banking.core.response.WithdrawResponse;
import org.banking.core.services.DepositService;
import org.banking.core.services.MoneyTransferService;
import org.banking.core.services.WithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WithdrawController {


    @Autowired
    private WithdrawService service;

    @GetMapping(value = "/withdraw")
    public String showDepositPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new WithdrawRequest());
        return "withdraw";
    }

    @PostMapping(value = "/withdraw")
    public String processDepositRequest(@ModelAttribute(value = "request")WithdrawRequest request,
                                        ModelMap modelMap) {
        WithdrawResponse responses = service.execute(request);
        if (responses.isCompleted()) {
            return "withdrawSuccess";
        } else {
            modelMap.addAttribute("errors", responses.getErrors());
            return "withdraw";
        }
    }
}


