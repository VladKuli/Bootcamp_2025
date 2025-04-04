package org.banking.web_ui.controllers.userControllers;

import org.banking.core.database.JpaCardRepository;
import org.banking.core.domain.BankAccount;
import org.banking.core.domain.Card;
import org.banking.core.domain.IBAN;
import org.banking.core.dto.bank_account.BankAccountDTO;
import org.banking.core.dto.iban.IbanDTO;
import org.banking.core.mapper.iban.IbanMapper;
import org.banking.core.request.operations.MoneyTransferRequest;
import org.banking.core.response.operations.MoneyTransferResponse;
import org.banking.core.services.bankAccount.GetCurrentBankAccountService;
import org.banking.core.services.iban.CurrentUserIbanService;
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

@Controller
public class MoneyTransferController {

    @Autowired
    private MoneyTransferService service;

    @Autowired
    private CurrentUserIbanService ibanService;

    @Autowired
    private JpaCardRepository jpaCardRepository;

    private static final Logger logger = LoggerFactory.getLogger(MoneyTransferController.class);

    @GetMapping(value = "/moneyTransfer")
    public String showMoneyTransferPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new MoneyTransferRequest());

        List<String> iban = ibanService.getIbanNumber();

            logger.info("Adding attribute iban {}", iban);
            modelMap.addAttribute("iban", iban);

        return "moneyTransfer";
    }

    @PostMapping(value = "/moneyTransfer")
    public String processMoneyTransferRequest(@ModelAttribute(value = "request")MoneyTransferRequest request,
                                           ModelMap modelMap) {
        logger.info("Proceeding request for transaction: {}", request);
        MoneyTransferResponse responses = service.execute(request);

        List<IbanDTO> ibanDTOS = ibanService.getIbanDTO();
        ibanDTOS.stream()
                .flatMap(ibanDTO -> ibanDTO.getCardNumbers().stream())
                .distinct()
                .forEach(cardNumber -> jpaCardRepository.withdrawCard(cardNumber, request.getAmount()));

        if (responses.isCompleted()) {

            logger.info("Success of request {}", request);
            return "moneyTransferSuccess";
        } else {

            modelMap.addAttribute("errors", responses.getErrors());

            return "moneyTransfer";
        }
    }
}