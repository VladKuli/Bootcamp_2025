package org.banking.web_ui.controllers.userControllers;

import org.banking.core.domain.Card;
import org.banking.core.request.operations.DepositRequest;
import org.banking.core.response.operations.DepositResponse;
import org.banking.core.services.operations.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class DepositController {

    @Autowired
    private DepositService service;

    @GetMapping(value = "/deposit")
    public String showDepositPage(ModelMap modelMap) {
        List<Card> cards = service.getUsersCards();
        modelMap.addAttribute("cards", cards);
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
