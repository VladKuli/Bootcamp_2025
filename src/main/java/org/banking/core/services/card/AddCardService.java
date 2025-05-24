package org.banking.core.services.card;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.banking.core.database.JpaCardRepository;
import org.banking.core.domain.Card;
import org.banking.core.request.card.AddCardRequest;
import org.banking.core.response.card.AddCardResponse;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AddCardService {

    private final JpaCardRepository jpaCardRepository;
    private final CardBuilderService cardBuilding;

    public AddCardResponse execute(AddCardRequest request) {

        Optional<Card> card = cardBuilding.execute(request);

        log.info("Saving card: {}", card.get());
        card.ifPresent(jpaCardRepository::save);

        boolean cardExists = card.flatMap(c -> jpaCardRepository
                .existsByCardNumber(c.getCardNumber()))
                .isPresent();

        if (cardExists) {

            log.info("Successfully adding card: {}", card.get());
            return new AddCardResponse(true);
        } else {
            return new AddCardResponse(false);
        }
    }

}