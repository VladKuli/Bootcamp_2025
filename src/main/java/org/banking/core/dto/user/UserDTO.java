package org.banking.core.dto.user;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {

    private String personalCode;
    private String password;
    private String role;
}
