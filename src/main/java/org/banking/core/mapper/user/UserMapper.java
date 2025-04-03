package org.banking.core.mapper.user;

import org.banking.core.domain.BankAccount;
import org.banking.core.domain.IBAN;
import org.banking.core.domain.User;
import org.banking.core.dto.bank_account.BankAccountDTO;
import org.banking.core.dto.user.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toDto(User user);

    User toEntity(UserDTO userDTO);


}
