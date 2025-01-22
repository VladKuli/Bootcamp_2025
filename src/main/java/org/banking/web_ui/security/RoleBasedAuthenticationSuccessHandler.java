package org.banking.web_ui.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RoleBasedAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        var authorities = authentication.getAuthorities();

        if (authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            response.sendRedirect("/admin");
        } else if (authorities.contains(new SimpleGrantedAuthority("ROLE_USER"))) {
            response.sendRedirect("/user");
        } else {
            response.sendRedirect("/access-denied");
        }
    }
}
