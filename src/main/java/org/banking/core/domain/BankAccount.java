package org.banking.core.domain;

import lombok.*;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="bank_accounts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BankAccount {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="surname", nullable = false)
    private String surname;

    @Column(name="personal_code", nullable = false, unique = true)
    private String personalCode;

    @Column(name="balance")
    @Builder.Default
    private Integer balance = 0;

    @Column(name = "iban", unique = true, nullable = false)
    private String IBAN;

    @OneToMany(mappedBy = "bankAccount", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Card> cards;

    @OneToMany(mappedBy = "fromAccount", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> outgoingTransactions;

    @OneToMany(mappedBy = "toAccount", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> incomingTransactions;
}



