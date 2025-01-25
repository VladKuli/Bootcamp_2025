package org.banking.core.request.user;

import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
public class AddUserRequest {

    private String personalCode;
    private String password;
    private String role;

    public AddUserRequest(String personalCode, String password, String role) {
        this.personalCode = personalCode;
        this.password = password;
        this.role = role;
    }
}
