package org.banking.core;

import org.banking.core.UI.UserInterface;
import org.banking.core.domain.BankAccount;

public class Main {

    public static void main(String[] args) {

        BankAccount source = new BankAccount(10000);
        BankAccount target = new BankAccount(100);

        UserInterface userInterface = new UserInterface();

        userInterface.start(source,target);
    }
}
