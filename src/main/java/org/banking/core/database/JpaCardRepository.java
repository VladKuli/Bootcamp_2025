package org.banking.core.database;

import org.banking.core.domain.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaCardRepository extends JpaRepository<Card, Long> {


    Optional<Card> existsByCardNumber(String cardNumber);

    @Query("Select c FROM Card c WHERE c.cardNumber=?1")
    void deleteByCardNumber(String CardNumber);
}
