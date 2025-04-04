package org.banking.core.response.bankAccount;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.banking.core.domain.BankAccount;
import org.banking.core.dto.bank_account.BankAccountDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class GetAllBankAccountsResponse {

    private List<BankAccountDTO> bankAccountDTOS;

}
