package org.banking.core.database;

import org.banking.core.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaTransactionRepository extends JpaRepository<Transaction, Long> {

}