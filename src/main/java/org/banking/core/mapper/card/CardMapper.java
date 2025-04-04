package org.banking.core.mapper.card;

import org.banking.core.domain.Card;
import org.banking.core.dto.card.CardDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CardMapper {

    CardDTO toDTO(Card card);

    Card toEntity(CardDTO cardDTO);
}
