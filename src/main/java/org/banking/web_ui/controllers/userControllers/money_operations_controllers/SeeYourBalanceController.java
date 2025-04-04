package org.banking.web_ui.controllers.userControllers.money_operations_controllers;

import org.banking.core.dto.iban.IbanDTO;
import org.banking.core.request.operations.SeeYourBalanceRequest;
import org.banking.core.response.operations.SeeYourBalanceResponse;
import org.banking.core.services.iban.CurrentUserIbanService;
import org.banking.core.services.operations.SeeYourBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class SeeYourBalanceController {

    @Autowired
    private SeeYourBalanceService service;

    @Autowired
    private CurrentUserIbanService ibanService;

    @GetMapping(value = "/seeYourBalance")
    public String showSeeYourBalancePage(@ModelAttribute(value = "request") SeeYourBalanceRequest request,
                                         ModelMap modelMap) {
        modelMap.addAttribute("request", new SeeYourBalanceRequest());

        SeeYourBalanceResponse response = service.execute(request);

        modelMap.addAttribute("bankAccount", response.getBankAccountDTO());

        List<IbanDTO> ibanList = ibanService.getIbanDTO();
        modelMap.addAttribute("ibanList", ibanList);

        return "seeYourBalance";
    }

}