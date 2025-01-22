package org.example.core.domain;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankAccount {

    private double balance;

    public void deposit(double amount) {
        if(amount > 0) {
            balance += amount;
            System.out.println("Deposited: " + amount);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrew: " + amount);
        } else if(amount > balance) {
            System.out.println("Insufficient funds.");
        } else {
            System.out.println("Withdraw amount must be positive.");
        }
    }
}
