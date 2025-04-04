package org.banking.core.database;

import org.banking.core.domain.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface JpaCardRepository extends JpaRepository<Card, Long> {


    Optional<Card> existsByCardNumber(String cardNumber);

    @Modifying
    @Transactional
    @Query("DELETE FROM Card c WHERE c.cardNumber = ?1")
    void deleteByCardNumber(String cardNumber);

    @Modifying
    @Transactional
    @Query("UPDATE Card c SET c.balance = ?2 WHERE c.cardNumber = ?1")
    void updateCard(String cardNumber, int value);

    @Modifying
    @Transactional
    @Query("UPDATE Card c SET c.balance = c.balance -?2 WHERE c.cardNumber = ?1")
    void withdrawCard(String cardNumber, int value);

    @Modifying
    @Transactional
    @Query("UPDATE Card c SET c.balance = c.balance +?2 WHERE c.cardNumber = ?1")
    void depositOnCard(String cardNumber, int value);
}
