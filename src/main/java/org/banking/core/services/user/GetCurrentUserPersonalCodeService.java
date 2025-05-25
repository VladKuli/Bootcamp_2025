package org.banking.core.services.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GetCurrentUserPersonalCodeService {


    public String getCurrentUserPersonalCode() {
        log.info("Attempting to retrieve the personal code of the currently authenticated user");

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            log.info("Successfully retrieved personal code from UserDetails: {}", username);
            return username;
        } else {
            String principalString = principal.toString();
            log.warn("Principal is not an instance of UserDetails, using principal's string representation: {}", principalString);
            return principalString;
        }
    }
}
