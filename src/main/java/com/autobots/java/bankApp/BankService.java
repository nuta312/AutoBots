package com.autobots.java.bankApp;

public class BankService {
    public static boolean transfer(BankAccount from, BankAccount to, double amount) {
        if (from.withdraw(amount)) {
            double converted = ExchangeRate.convert(amount, from.getCurrency(), to.getCurrency());
            to.deposit(converted);
            from.addTransaction("TRANSFER OUT", amount);
            to.addTransaction("TRANSFER IN", converted);
            return true;
        }
        return false;
    }
}