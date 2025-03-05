package org.banking.core.database;

import org.banking.core.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaTransactionRepository extends JpaRepository<Transaction, Long> {

}