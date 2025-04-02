package org.banking.web_ui.controllers.userControllers;

import org.banking.core.domain.Card;
import org.banking.core.domain.IBAN;
import org.banking.core.request.operations.WithdrawRequest;
import org.banking.core.response.operations.WithdrawResponse;
import org.banking.core.services.operations.WithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class WithdrawController {


    @Autowired
    private WithdrawService service;

    @GetMapping(value = "/withdraw")
    public String showDepositPage(ModelMap modelMap) {
        List<IBAN> ibanList = service.getUsersCards();
        modelMap.addAttribute("IBAN", ibanList);
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


