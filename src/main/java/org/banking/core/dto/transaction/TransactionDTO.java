package org.banking.core.dto.transaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.banking.core.enums.TransactionType;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDTO {

    @NotNull(message = "From account name cannot be null")
    @NotEmpty(message = "From account name cannot be empty")
    private String fromAccountName;

    @NotNull(message = "From account surname cannot be null")
    @NotEmpty(message = "From account surname cannot be empty")
    private String fromAccountSurname;

    @NotNull(message = "To account name cannot be null")
    @NotEmpty(message = "To account name cannot be empty")
    private String toAccountName;

    @NotNull(message = "To account surname cannot be null")
    @NotEmpty(message = "To account surname cannot be empty")
    private String toAccountSurname;

    @NotNull
    private Integer amount;

    @NotNull
    private TransactionType type;

    @NotNull(message = "Description cannot be null")
    @NotEmpty(message = "Description cannot be empty")
    private String description;

    @NotNull
    private LocalDateTime timeStamp;

}
