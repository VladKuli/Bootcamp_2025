package org.banking.core.services.card;

import org.banking.core.database.JpaCardRepository;
import org.banking.core.domain.Card;
import org.banking.core.domain.IBAN;
import org.banking.core.request.card.AddCardRequest;
import org.banking.core.response.card.AddCardResponse;
import org.banking.core.services.bankAccount.DeleteBankAccountService;
import org.banking.core.services.bankAccount.GetCurrentBankAccountService;
import org.banking.core.services.iban.CurrentUserIbanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddCardService {

    @Autowired
    private JpaCardRepository jpaCardRepository;

    @Autowired
    private CardNumberGeneratorService numberGeneratorService;

    @Autowired
    private CurrentUserIbanService ibanService;

    private static final Logger logger = LoggerFactory.getLogger(AddCardService.class);

    public AddCardResponse execute(AddCardRequest request) {

        Optional<Card> card = cardBuilding(request);

        logger.info("Saving card: {}", card.get());
        card.ifPresent(c -> jpaCardRepository.save(c));
        boolean cardExists = card.flatMap(c -> jpaCardRepository
                .existsByCardNumber(c.getCardNumber()))
                .isPresent();

        if (cardExists) {

            logger.info("Successfully adding card: {}", card.get());
            return new AddCardResponse(true);
        } else {
            return new AddCardResponse(false);
        }
    }

    private Optional<Card> cardBuilding(AddCardRequest request) {

        List<IBAN> iban = ibanService.getIBAN();

        Optional<IBAN> ibanOptional = iban.stream()
                .filter(i -> i.getIbanNumber().equals(request.getIban()))
                .findFirst();

        if (ibanOptional.isPresent()) {
            Card card = Card.builder()
                    .cardNumber(numberGeneratorService.generateCardNumber())
                    .iban(ibanOptional.get())
                    .balance(ibanOptional.get().getBalance())
                    .type(request.getType())
                    .build();

            return Optional.of(card);
        }

        return Optional.empty();
    }
}
