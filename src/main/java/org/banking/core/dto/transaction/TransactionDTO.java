package org.banking.core.dto.transaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.banking.core.domain.TransactionType;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDTO {

    private String fromAccountName;
    private String fromAccountSurname;
    private String toAccountName;
    private String toAccountSurname;
    private Integer amount;
    private TransactionType type;
    private String description;
    private LocalDateTime timeStamp;



}
