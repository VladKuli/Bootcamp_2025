package org.banking.core.database;

import org.banking.core.domain.IBAN;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaIBANRepository extends JpaRepository<IBAN, Long> {


}
