package org.banking.core.mapper.transaction;

import org.banking.core.domain.Transaction;
import org.banking.core.dto.transaction.TransactionDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    TransactionDTO toDto(Transaction transaction);

    Transaction toEntity(TransactionDTO transactionDTO);
}
