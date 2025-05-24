package org.banking.core.services.card;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.banking.core.database.JpaCardRepository;
import org.banking.core.domain.Card;
import org.banking.core.request.card.DeleteCardRequest;
import org.banking.core.response.card.DeleteCardResponse;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeleteCardService {

    private final JpaCardRepository jpaCardRepository;


    public DeleteCardResponse execute(DeleteCardRequest request) {

        log.info("Deleting card with request: {}",request.getCardNumber());
         jpaCardRepository.deleteByCardNumber(request.getCardNumber());

        log.info("Is Card deleted check from this request: {}", request.getCardNumber());
        Optional<Card> card = jpaCardRepository.existsByCardNumber(request.getCardNumber());

        log.debug("Checking is CardPresent: {}",card);
        if (card.isPresent()) {

            log.warn("Error: Card is exist: {}",card);
            return new DeleteCardResponse(false);

        } else {

            log.warn("Card successfully deleted");
            return new DeleteCardResponse(true);
        }
    }
}