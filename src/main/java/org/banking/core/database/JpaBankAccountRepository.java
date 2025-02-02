package org.banking.core.database;

import org.banking.core.domain.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Component
public interface JpaBankAccountRepository extends JpaRepository<BankAccount, Long> {

    @Transactional
    @Modifying
    @Query(value = "update bank_accounts\n" +
            "set balance = CASE \n" +
            "  WHEN personal_code = ?1\n" +
            "    THEN balance - ?3\n" +
            "  WHEN personal_code = ?2\n" +
            "    THEN balance + ?3\n" +
            "    End;", nativeQuery = true)
    void bankTransfer(String personalCode, String anotherPersonalCode
            , int value);

    void deleteByPersonalCode(String personalCode);

    @Query("SELECT b FROM BankAccount b WHERE b.personalCode=?1")
    Optional<BankAccount> seeYourBalance(String personalCode);

    @Transactional
    @Modifying
    @Query("Update BankAccount b set b.balance = null where b.personalCode = ?1")
    void closeAccount(String personalCode);

    @Transactional
    @Modifying
    @Query("Update BankAccount b set b.balance = 0 where b.personalCode = ?1")
    void openAccount(String personalCode);

    @Transactional
    @Modifying
    @Query(value = "Update bank_accounts set balance = balance + ?2 where personal_code = ?1", nativeQuery = true)
    void deposit(String personalCode, int value);

    @Transactional
    @Modifying
    @Query(value = "Update bank_accounts set balance = balance - ?2 where personal_code = ?1", nativeQuery = true)
    void withdraw(String personalCode, int value);


}
