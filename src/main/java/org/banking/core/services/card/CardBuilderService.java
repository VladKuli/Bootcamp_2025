package org.banking.core.services.card;


import lombok.RequiredArgsConstructor;
import org.banking.core.domain.Card;
import org.banking.core.domain.IBAN;
import org.banking.core.request.card.AddCardRequest;
import org.banking.core.services.iban.CurrentUserIbanService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CardBuilderService {


    private final CardNumberGeneratorService numberGeneratorService;
    private final CurrentUserIbanService ibanService;

    public Optional<Card> execute(AddCardRequest request) {
         return cardBuilding(request);
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
