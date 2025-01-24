package org.banking.web_ui.controllers.userControllers;

import org.banking.core.request.SeeYourBalanceRequest;
import org.banking.core.response.SeeYourBalanceResponse;
import org.banking.core.services.SeeYourBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SeeYourBalanceController {

    @Autowired
    private SeeYourBalanceService service;

    @GetMapping(value = "/seeYourBalance")
    public String showOpenAccountPage(ModelMap modelMap) {
        SeeYourBalanceResponse response = service.execute(new SeeYourBalanceRequest());
        modelMap.addAttribute("bankAccount", response.getBankAccount().get());
        return "/seeYourBalance";
    }
}