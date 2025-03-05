package org.banking.web_ui.security;

import org.banking.core.database.JpaUserRepository;
import org.banking.core.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

@Configuration
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private JpaUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByPersonalCode(username);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        User foundUser = user.get();
        return org.springframework.security.core.userdetails.User.builder()
                .username(foundUser.getPersonalCode())
                .password(foundUser.getPassword())
                .roles(foundUser.getRole())
                .build();
    }
}