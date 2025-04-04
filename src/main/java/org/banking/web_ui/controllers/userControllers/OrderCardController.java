package org.banking.web_ui.controllers.userControllers;

import org.banking.core.domain.BankAccount;
import org.banking.core.domain.IBAN;
import org.banking.core.dto.bank_account.BankAccountDTO;
import org.banking.core.request.card.AddCardRequest;
import org.banking.core.response.card.AddCardResponse;
import org.banking.core.services.bankAccount.GetCurrentBankAccountService;
import org.banking.core.services.card.AddCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class OrderCardController {

    @Autowired
    private AddCardService service;

    @Autowired
    private GetCurrentBankAccountService getCurrentBankAccountService;

    @GetMapping(value = "/card-order")
    public String showCardOrderPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new AddCardRequest());
        BankAccountDTO bankAccountDTO = getCurrentBankAccountService.getBankAccountDTO();
        List<String> iban = bankAccountDTO.getIbanNumbers();
            modelMap.addAttribute("bankAccount", bankAccountDTO);
            modelMap.addAttribute("iban", iban);

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
