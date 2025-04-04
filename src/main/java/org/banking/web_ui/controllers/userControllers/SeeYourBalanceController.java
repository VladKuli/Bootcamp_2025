package org.banking.web_ui.controllers.userControllers;

import org.banking.core.domain.BankAccount;
import org.banking.core.domain.Card;
import org.banking.core.domain.IBAN;
import org.banking.core.dto.iban.IbanDTO;
import org.banking.core.request.operations.DepositRequest;
import org.banking.core.request.operations.MoneyTransferRequest;
import org.banking.core.request.operations.SeeYourBalanceRequest;
import org.banking.core.response.bankAccount.GetCurrentBankAccountResponse;
import org.banking.core.response.operations.SeeYourBalanceResponse;
import org.banking.core.services.bankAccount.GetCurrentBankAccountService;
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
    private GetCurrentBankAccountService getCurrentBankAccountService;

    @GetMapping(value = "/seeYourBalance")
    public String showSeeYourBalancePage(@ModelAttribute(value = "request") SeeYourBalanceRequest request,
                                         ModelMap modelMap) {
        modelMap.addAttribute("request", new SeeYourBalanceRequest());

        SeeYourBalanceResponse response = service.execute(request);

        modelMap.addAttribute("bankAccount", response.getBankAccountDTO());

        List<IbanDTO> ibanList = getCurrentBankAccountService.getIbanDTO();
        modelMap.addAttribute("ibanList", ibanList);

        return "seeYourBalance";
    }

}