package org.banking.web_ui.controllers;

import org.banking.core.database.JpaCardRepository;
import org.banking.core.domain.BankAccount;
import org.banking.core.domain.Card;
import org.banking.core.domain.IBAN;
import org.banking.core.services.bankAccount.GetCurrentBankAccountService;
import org.banking.core.services.card.CurrentUserCardService;
import org.banking.core.services.iban.CurrentUserIbanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class IndexController {

    @Autowired
    private GetCurrentBankAccountService getCurrentBankAccountService;

    @Autowired
    private CurrentUserIbanService ibanService;

    @Autowired
    private JpaCardRepository jpaCardRepository;

    @Autowired
    private CurrentUserCardService cardService;

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @GetMapping(value = "/admin")
    public String index() {
        return "indexAdmin";
    }

    @GetMapping(value = "/user")
    public String user(ModelMap modelMap) {
        Optional<BankAccount> bankAccountOpt = getCurrentBankAccountService.get();

        if (bankAccountOpt.isPresent()) {
            BankAccount bankAccount = bankAccountOpt.get();
            List<IBAN> ibanList = ibanService.getIBAN();

            for (IBAN iban : ibanList) {
                for (Card card : iban.getCards()) {
                    jpaCardRepository.updateCard(card.getCardNumber(), iban.getBalance());
                    logger.info("Updated card {} with IBAN {} balance: {}", card.getCardNumber(), iban.getIbanNumber(), iban.getBalance());
                }
            }

            modelMap.addAttribute("bankAccount", bankAccount);
            modelMap.addAttribute("iban", ibanService.getIbanNumber());
            modelMap.addAttribute("cards",cardService.getCardsDTO());
        }

        return "indexUser";
    }
}
