package com.autobots.java.bankApp;

public class DepositAccount extends BankAccount {
    public DepositAccount(Client owner, Currency currency) {
        super(owner, currency);
    }

    public void deposit(double amount) {
        balance += amount;
        addTransaction("DEPOSIT", amount);
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            addTransaction("WITHDRAW", amount);
            return true;
        }
        return false;
    }
}