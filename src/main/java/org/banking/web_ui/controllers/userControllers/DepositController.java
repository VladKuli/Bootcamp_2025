package org.banking.web_ui.controllers.userControllers;

import org.banking.core.request.DepositRequest;
import org.banking.core.request.WithdrawRequest;
import org.banking.core.response.DepositResponse;
import org.banking.core.response.WithdrawResponse;
import org.banking.core.services.DepositService;
import org.banking.core.services.WithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DepositController {

    @Autowired
    private DepositService service;

    @GetMapping(value = "/deposit")
    public String showDepositPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new DepositRequest());
        return "deposit";
    }

    @PostMapping(value = "/deposit")
    public String processDepositRequest(@ModelAttribute(value = "request")DepositRequest request,
                                           ModelMap modelMap) {
        DepositResponse responses = service.execute(request);
        if (responses.isCompleted()) {
            return "depositSuccess";
        } else {
            modelMap.addAttribute("errors", responses.getErrors());
            return "deposit";
        }
    }
}
