package org.example.core.UI;

import org.example.core.domain.BankAccount;
import org.example.core.services.TransactionService;

import java.util.Scanner;

public class UserInterface {

    private final TransactionService transactionService = new TransactionService();

    private final Scanner scanner = new Scanner(System.in);

    public void start(BankAccount sourceBankAccount, BankAccount targetBankAccount) {

        boolean running = true;

        while(running) {
            System.out.println(" Choose an action:");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Print Balance");
            System.out.println("4. Transfer");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> deposit(sourceBankAccount);
                case 2 -> withdraw(sourceBankAccount);
                case 3 -> printBalance(sourceBankAccount);
                case 4 -> transfer(sourceBankAccount, targetBankAccount);
                case 5 -> {
                    running = false;
                    System.out.println("Exiting program. Goodbye!");
                }
                default -> System.out.println("Invalid choice. Try again.");
            }

        }


    }

    private void deposit(BankAccount account) {
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        account.deposit(amount);
    }

    private void withdraw(BankAccount account) {
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        account.withdraw(amount);
    }

    private void printBalance(BankAccount account) {
        System.out.println("Current Balance: " + account.getBalance());
    }

    private void transfer(BankAccount source, BankAccount target) {
        System.out.print("Enter amount to transfer: ");
        double amount = scanner.nextDouble();
        transactionService.transferMoney(source, target, amount);
        System.out.println("Target account balance: " + target.getBalance());
    }

}
