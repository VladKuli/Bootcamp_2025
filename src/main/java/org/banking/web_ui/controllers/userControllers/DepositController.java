package org.banking.web_ui.controllers.userControllers;

import org.banking.core.database.JpaCardRepository;
import org.banking.core.domain.Card;
import org.banking.core.domain.IBAN;
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
    @Autowired
    private JpaCardRepository cardRepository;

    @GetMapping(value = "/deposit")
    public String showDepositPage(ModelMap modelMap) {
        List<IBAN> ibanList = service.getUsersIBANS();
        modelMap.addAttribute("IBAN", ibanList);
        modelMap.addAttribute("request", new DepositRequest());
        return "deposit";
    }

    @PostMapping(value = "/deposit")
    public String processDepositRequest(@ModelAttribute(value = "request")DepositRequest request,
                                           ModelMap modelMap) {
        DepositResponse responses = service.execute(request);
        List<Card> cardsList = service.getUsersIBANS().get(0).getCards();
        for (Card card : cardsList) {
            cardRepository.depositOnCard(card.getCardNumber(), request.getAmount());
        }
        if (responses.isCompleted()) {
            return "depositSuccess";
        } else {
            modelMap.addAttribute("errors", responses.getErrors());
            return "deposit";
        }
    }
}
