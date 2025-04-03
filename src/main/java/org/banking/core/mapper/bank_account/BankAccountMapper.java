package org.banking.core.mapper.bank_account;

import org.banking.core.domain.BankAccount;
import org.banking.core.domain.IBAN;
import org.banking.core.dto.bank_account.BankAccountDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface BankAccountMapper {

    @Mappings({
            @Mapping(source = "IBAN", target = "ibanNumbers", qualifiedByName = "ibanToStringList")
    })
    BankAccountDTO toDto(BankAccount bankAccount);

    BankAccount toEntity(BankAccountDTO bankAccountDto);

    @Named("ibanToStringList")
    default List<String> ibanToStringList(List<IBAN> ibans) {
        return ibans.stream().map(IBAN::getIbanNumber).collect(Collectors.toList());
    }
}
