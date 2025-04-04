package org.banking.core.services.card;

import org.banking.core.domain.Card;
import org.banking.core.dto.card.CardDTO;
import org.banking.core.mapper.card.CardMapper;
import org.banking.core.services.iban.CurrentUserIbanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CurrentUserCardService {

    @Autowired
    private CurrentUserIbanService ibanService;

    @Autowired
    private CardMapper cardMapper;

    public List<CardDTO> getCardsDTO() {
        List<Card> cards = ibanService.getIBAN().stream()
                .flatMap(iban -> iban.getCards().stream())
                .toList();

        return cards.stream()
                .map(card -> cardMapper.toDTO(card))
                .collect(Collectors.toList());
    }

}
