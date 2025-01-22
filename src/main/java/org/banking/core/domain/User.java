package org.banking.core.domain;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class User implements UserDetails {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name="personal_code", nullable = false)
    private String personalCode;
    @Column(name="password", nullable = false)
    private String password;
    @Column(name="role", nullable = false)
    private String role;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> role); // Простой способ добавить роль
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return personalCode;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
