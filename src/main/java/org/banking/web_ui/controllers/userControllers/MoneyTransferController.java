package org.banking.web_ui.controllers.userControllers;

import org.banking.core.request.operations.MoneyTransferRequest;
import org.banking.core.response.operations.MoneyTransferResponse;
import org.banking.core.services.operations.MoneyTransferService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MoneyTransferController {

    @Autowired
    private MoneyTransferService service;

    private static final Logger logger = LoggerFactory.getLogger(MoneyTransferController.class);

    @GetMapping(value = "/moneyTransfer")
    public String showMoneyTransferPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new MoneyTransferRequest());
        return "moneyTransfer";
    }

    @PostMapping(value = "/moneyTransfer")
    public String processDeleteUserRequest(@ModelAttribute(value = "request")MoneyTransferRequest request,
                                           ModelMap modelMap) {
        logger.info("Proceeding request for transaction: {}", request);
        MoneyTransferResponse responses = service.execute(request);

        if (responses.isCompleted()) {
            logger.info("Success of request {}", request);
            return "moneyTransferSuccess";
        } else {
            logger.warn("Errors: {}", responses);
            modelMap.addAttribute("errors", responses.getErrors());
            return "moneyTransfer";
        }
    }
}
