package org.banking.core.request.bankAccount;

import liquibase.pro.packaged.A;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@NoArgsConstructor

public class SearchBankAccountRequest {
    private String name;
    private String surname;
    private String personalCode;


    public SearchBankAccountRequest(String name, String surname, String personalCode) {
        this.name = name;
        this.surname = surname;
        this.personalCode = personalCode;
    }

    public boolean nameNullCheck() {
        return this.name != null && !this.name.isBlank();
    }

    public boolean surnameNullCheck() {
        return this.surname != null && !this.surname.isBlank();
    }

    public boolean personalCodeNullCheck() {
        return this.personalCode != null && !this.personalCode.isBlank();
    }
}
