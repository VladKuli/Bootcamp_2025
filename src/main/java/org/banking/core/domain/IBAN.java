package org.banking.core.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "iban")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IBAN {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "iban_number", nullable = false)
    private String ibanNumber;

    @Column(name = "balance", nullable = false)
    @Builder.Default
    private Integer balance = 0;

    @OneToMany(mappedBy = "iban", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Card> cards;

    @ManyToOne
    @JoinColumn(name = "bank_account_id", nullable = false)
    private BankAccount bankAccount;
}
