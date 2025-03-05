package org.banking.web_ui.controllers.userControllers;

import org.banking.core.request.card.AddCardRequest;
import org.banking.core.request.card.DeleteCardRequest;
import org.banking.core.response.card.AddCardResponse;
import org.banking.core.response.card.DeleteCardResponse;
import org.banking.core.services.card.AddCardService;
import org.banking.core.services.card.DeleteCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DeleteCardController {

        @Autowired
        private DeleteCardService service;

        @GetMapping(value = "/card-deleting")
        public String showCardOrderPage(ModelMap modelMap) {
            modelMap.addAttribute("request", new DeleteCardRequest());
            return "cardDeleting";
        }

        @PostMapping(value = "/card-deleting")
        public String processCardOrder(@ModelAttribute(value = "request") DeleteCardRequest request,
                                       ModelMap modelMap) {
            DeleteCardResponse response = service.execute(request);
                return "cardDeleting";
        }
}
