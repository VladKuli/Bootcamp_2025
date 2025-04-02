package org.banking.web_ui.controllers.userControllers;

import org.banking.core.database.JpaCardRepository;
import org.banking.core.domain.BankAccount;
import org.banking.core.domain.Card;
import org.banking.core.domain.IBAN;
import org.banking.core.request.operations.MoneyTransferRequest;
import org.banking.core.response.operations.MoneyTransferResponse;
import org.banking.core.services.bankAccount.GetCurrentBankAccountService;
import org.banking.core.services.operations.MoneyTransferService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class MoneyTransferController {

    @Autowired
    private MoneyTransferService service;

    @Autowired
    private GetCurrentBankAccountService getCurrentBankAccountService;

    @Autowired
    private JpaCardRepository jpaCardRepository;

    private static final Logger logger = LoggerFactory.getLogger(MoneyTransferController.class);

    @GetMapping(value = "/moneyTransfer")
    public String showMoneyTransferPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new MoneyTransferRequest());
        Optional<BankAccount> bankAccount = getCurrentBankAccountService.get();
        if(bankAccount.isPresent()) {
            List<IBAN> iban = bankAccount.get().getIBAN();
            logger.info("Adding attribute iban {}", iban);
            modelMap.addAttribute("iban", iban);
        }
        return "moneyTransfer";
    }

    @PostMapping(value = "/moneyTransfer")
    public String processDeleteUserRequest(@ModelAttribute(value = "request")MoneyTransferRequest request,
                                           ModelMap modelMap) {
        logger.info("Proceeding request for transaction: {}", request);
        MoneyTransferResponse responses = service.execute(request);
        List<Card> cardsList = getCurrentBankAccountService.getIBAN().get(0).getCards();

        for (Card card : cardsList) {
            jpaCardRepository.withdrawCard(card.getCardNumber(), request.getAmount());
        }
            logger.info("Success of request {}", request);
            return "moneyTransferSuccess";
    }
}