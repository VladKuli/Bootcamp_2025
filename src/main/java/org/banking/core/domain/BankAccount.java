package org.banking.core.domain;

import lombok.*;
import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name="bank_accounts")
@Builder
@AllArgsConstructor
public class BankAccount {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(name="name", nullable = false)
    private String name;
    @Column(name="surname", nullable = false)
    private String surname;
    @Column(name="personal_code", nullable = false)
    private String personalCode;
    @Builder.Default
    @Column(name="balance")
    private Integer balance = 0;

}
