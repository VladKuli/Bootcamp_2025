package org.banking.core.services.card;

import org.banking.core.database.JpaCardRepository;
import org.banking.core.domain.Card;
import org.banking.core.request.card.AddCardRequest;
import org.banking.core.response.card.AddCardResponse;
import org.banking.core.services.bankAccount.GetCurrentBankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddCardService {

    @Autowired
    private JpaCardRepository jpaCardRepository;

    @Autowired
    private GetCurrentBankAccountService getCurrentBankAccount;

    @Autowired
    private CardNumberGeneratorService numberGeneratorService;

    public AddCardResponse execute(AddCardRequest request) {
        Card card = Card.builder()
                .type(request.getType())
                .cardNumber(numberGeneratorService.generateCardNumber())
                .bankAccount(getCurrentBankAccount.get().get()).build();
        jpaCardRepository.save(card);
        boolean cardExists = jpaCardRepository.existsByCardNumber(card.getCardNumber()).isPresent();
        if (cardExists) {
            return new AddCardResponse(true);
        } else {
            return new AddCardResponse(false);
        }
    }

}
