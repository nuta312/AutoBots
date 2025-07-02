package com.autobots.java.bankApp;

public class CreditAccount extends BankAccount {
    private final double creditLimit = 1000;

    public CreditAccount(Client owner, Currency currency) {
        super(owner, currency);
    }

    public void deposit(double amount) {
        balance += amount;
        addTransaction("DEPOSIT", amount);
    }

    public boolean withdraw(double amount) {
        if (amount <= balance + creditLimit) {
            balance -= amount;
            addTransaction("WITHDRAW", amount);
            return true;
        }
        return false;
    }
}