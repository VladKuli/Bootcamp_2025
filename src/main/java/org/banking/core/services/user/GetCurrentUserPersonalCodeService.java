package org.banking.core.services.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class GetCurrentUserPersonalCodeService {

    private static final Logger logger = LoggerFactory.getLogger(GetCurrentUserPersonalCodeService.class);

    public String getCurrentUserPersonalCode() {
        logger.info("Attempting to retrieve the personal code of the currently authenticated user");

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            logger.info("Successfully retrieved personal code from UserDetails: {}", username);
            return username;
        } else {
            String principalString = principal.toString();
            logger.warn("Principal is not an instance of UserDetails, using principal's string representation: {}", principalString);
            return principalString;
        }
    }
}
