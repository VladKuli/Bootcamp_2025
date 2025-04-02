package org.banking.core.database;

import org.banking.core.domain.BankAccount;
import org.banking.core.domain.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface JpaBankAccountRepository extends JpaRepository<BankAccount, Long> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE iban " +
            "SET balance = CASE " +
            "  WHEN iban_number = ?1 THEN balance - ?3 " +
            "  WHEN iban_number = ?2 THEN balance + ?3 " +
            "END " +
            "WHERE iban_number IN (?1, ?2)", nativeQuery = true)
    void bankTransfer(String IBAN, String payeeIBAN, int value);

    @Transactional
    @Modifying
    @Query(value = "Update bank_accounts set balance = balance + ?2 where personal_code = ?1", nativeQuery = true)
    void deposit(String personalCode, int value);

    @Transactional
    @Modifying
    @Query(value = "Update Card c set c.balance = c.balance + ?2 WHERE c.cardNumber = ?1")
    void cardDeposit(String cardNumber, int value);

    @Transactional
    @Modifying
    @Query("UPDATE IBAN i SET i.balance = i.balance + ?2 WHERE i.id = ?1")
    void ibanDeposit(Long id, int amount);


    @Transactional
    @Modifying
    @Query(value = "UPDATE iban SET balance = balance - ?1 WHERE iban_number = ?2", nativeQuery = true)
    void deductBalanceForIban(int value, String senderIBAN);

    @Transactional
    @Modifying
    @Query(value = "UPDATE iban SET balance = balance + ?1 WHERE iban_number = ?2", nativeQuery = true)
    void addBalanceForIban(int value, String receiverIBAN);

    @Query(value = """
    SELECT ba.* FROM bank_accounts ba
    JOIN iban i ON i.bank_account_id = ba.id
    WHERE i.iban_number = :ibanNumber
    """, nativeQuery = true)
    Optional<BankAccount> findByIBANNumber(@Param("ibanNumber") String ibanNumber);

    void deleteByPersonalCode(String personalCode);

    List<BankAccount> findByName(String name);

    List<BankAccount> findBySurname(String surname);

    List<BankAccount> findByNameAndSurname(String name, String surname);

    List<BankAccount> findByPersonalCode(String personalCode);


    List<BankAccount> findByNameAndPersonalCode(String name, String personalCode);

    List<BankAccount> findBySurnameAndPersonalCode(String surname, String personalCode);

    List<BankAccount> findByNameAndSurnameAndPersonalCode(String name, String surname, String personalCode);

}
