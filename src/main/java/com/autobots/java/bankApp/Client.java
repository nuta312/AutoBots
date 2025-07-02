package com.autobots.java.bankApp;

import java.util.*;

public class Client {
    private final String fullName;
    private final String clientId;
    private final String pin;
    private final List<BankAccount> accounts = new ArrayList<>();

    public Client(String fullName, String pin) {
        this.fullName = fullName;
        this.clientId = UUID.randomUUID().toString();

        this.pin = pin;
    }

    public boolean authenticate(String inputPin) {
        return this.pin.equals(inputPin);
    }

    public String getFullName() { return fullName; }
    public String getClientId() { return clientId; }
    public List<BankAccount> getAccounts() { return accounts; }
    public void addAccount(BankAccount acc) { accounts.add(acc); }
}