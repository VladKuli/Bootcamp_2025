package org.banking.core.services.card;

import org.banking.core.database.JpaCardRepository;
import org.banking.core.domain.Card;
import org.banking.core.request.card.DeleteCardRequest;
import org.banking.core.response.card.DeleteCardResponse;
import org.banking.core.services.bankAccount.GetCurrentBankAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteCardService {

    @Autowired
    private JpaCardRepository jpaCardRepository;

    private static final Logger logger = LoggerFactory.getLogger(DeleteCardService.class);

    public DeleteCardResponse execute(DeleteCardRequest request) {

        logger.info("Deleting card with request: {}",request.getCardNumber());
         jpaCardRepository.deleteByCardNumber(request.getCardNumber());
        logger.info("Is Card deleted check from this request: {}", request.getCardNumber());
        Optional<Card> card = jpaCardRepository.existsByCardNumber(request.getCardNumber());
        logger.debug("Checking is CardPresent: {}",card);
        if (card.isPresent()) {
            logger.warn("Error: Card is exist: {}",card);
            return new DeleteCardResponse(false);
        } else {
            logger.warn("Card successfully deleted");
            return new DeleteCardResponse(true);
        }
    }
}