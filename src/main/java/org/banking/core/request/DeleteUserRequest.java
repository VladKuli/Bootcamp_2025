package org.banking.core.request;

import liquibase.pro.packaged.D;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteUserRequest {

    private String personalCode;
}
