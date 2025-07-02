package com.autobots.java.bankApp;

import java.util.*;

public abstract class BankAccount {
    protected String accountNumber;
    protected double balance;
    protected final Client owner;
    protected final Currency currency;
    protected final List<Transaction> transactions = new ArrayList<>();

    public BankAccount(Client owner, Currency currency) {
        this.accountNumber = UUID.randomUUID().toString();
        this.owner = owner;
        this.currency = currency;
    }

    public abstract boolean withdraw(double amount);
    public abstract void deposit(double amount);

    public double getBalance() { return balance; }
    public String getAccountNumber() { return accountNumber; }
    public Currency getCurrency() { return currency; }
    public List<Transaction> getTransactions() { return transactions; }
    public Client getOwner() { return owner; }

    public void addTransaction(String type, double amount) {
        transactions.add(new Transaction(type, amount));
    }
}