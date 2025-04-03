package org.banking.core.mapper.iban;

import org.banking.core.domain.BankAccount;
import org.banking.core.domain.Card;
import org.banking.core.domain.IBAN;
import org.banking.core.dto.iban.IbanDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface IbanMapper {

    @Mapping(source = "cards", target = "cardNumbers", qualifiedByName = "cardToStringList")
    IbanDTO toDto(IBAN iban);

    IBAN toEntity(IbanDTO ibanDTO);

    @Named("cardToStringList")
    default List<String> cardToStringList(List<Card> cards) {
        return cards.stream().map(Card::getCardNumber).collect(Collectors.toList());
    }
}
