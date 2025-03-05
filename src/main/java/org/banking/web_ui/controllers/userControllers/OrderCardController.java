package org.banking.web_ui.controllers.userControllers;

import org.banking.core.request.card.AddCardRequest;
import org.banking.core.response.card.AddCardResponse;
import org.banking.core.services.card.AddCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OrderCardController {

    @Autowired
    private AddCardService service;

    @GetMapping(value = "/card-order")
    public String showCardOrderPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new AddCardRequest());
        return "cardOrder";
    }

    @PostMapping(value = "/card-order")
    public String processCardOrder(@ModelAttribute(value = "request")AddCardRequest request,
                                   ModelMap modelMap) {
        AddCardResponse response = service.execute(request);
        if (response.isOrdered()) {
            return "successOrdered";
        } else {
            return "cardOrder";
        }
    }
}
