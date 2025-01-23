package org.banking.core.database;

import org.banking.core.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface JpaUserRepository extends JpaRepository<User, Long> {

    void deleteByPersonalCode(String personalCode);

    Optional<User> findByPersonalCode(String personalCode);
}
