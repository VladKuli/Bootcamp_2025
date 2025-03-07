package org.banking.core.services.card;

import org.banking.core.database.JpaCardRepository;
import org.banking.core.domain.Card;
import org.banking.core.domain.IBAN;
import org.banking.core.request.card.AddCardRequest;
import org.banking.core.response.card.AddCardResponse;
import org.banking.core.services.bankAccount.GetCurrentBankAccountService;
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
    private GetCurrentBankAccountService getCurrentBankAccountService;

    public AddCardResponse execute(AddCardRequest request) {

        List<IBAN> iban = getCurrentBankAccountService.getIBAN();

        Optional<IBAN> ibanOptional = iban.stream()
                .filter(i -> i.getIbanNumber().equals(request.getIban()))
                .findFirst();

        Card card = Card.builder()
                .cardNumber(numberGeneratorService.generateCardNumber())
                .iban(ibanOptional.get())
                .type(request.getType())
                .build();

        jpaCardRepository.save(card);
        boolean cardExists = jpaCardRepository.existsByCardNumber(card.getCardNumber()).isPresent();
        if (cardExists) {
            return new AddCardResponse(true);
        } else {
            return new AddCardResponse(false);
        }
    }

}
