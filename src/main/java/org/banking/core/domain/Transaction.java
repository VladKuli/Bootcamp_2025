package org.banking.core.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "transactions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "from_bank_accounts_id", nullable = false)
    private BankAccount fromAccount;

    @ManyToOne
    @JoinColumn(name = "to_bank_accounts_id", nullable = false)
    private BankAccount toAccount;

    @Column(name = "amount", nullable = false)
    private Integer amount;

    @Column(name = "type", nullable = false)
    private TransactionType type;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timeStamp;

    @PrePersist
    protected void onCreate() {
        timeStamp = LocalDateTime.now();
    }
}