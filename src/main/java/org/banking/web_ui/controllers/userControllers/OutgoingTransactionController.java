package org.banking.web_ui.controllers.userControllers;

import org.banking.core.domain.BankAccount;
import org.banking.core.dto.bank_account.BankAccountDTO;
import org.banking.core.dto.transaction.TransactionDTO;
import org.banking.core.services.bankAccount.GetCurrentBankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class OutgoingTransactionController {

    @Autowired
    private GetCurrentBankAccountService getCurrentBankAccount;

    @GetMapping("/seeOutgoingTransactions")
    public String getOutgoingTransactions(ModelMap modelMap) {

        BankAccountDTO bankAccountDTO = getCurrentBankAccount.getBankAccountDTO();
        List<TransactionDTO> transactionDTOS = getCurrentBankAccount.getBankAccountOutgoingTransactions();
            modelMap.addAttribute("bankAccount", bankAccountDTO);
            modelMap.addAttribute("outgoingTransactions", transactionDTOS);
        return "seeOutgoingTransactions";
    }
}

