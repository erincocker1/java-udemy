package org.example.Section16.ImmutableChallenges;

import java.util.HashMap;
import java.util.Map;

public class BankAccount {

    private final AccountType type;

    private double balance;

    private Map<Long, Transaction> transactions = new HashMap<>();

    BankAccount(AccountType type, double balance) {
        this.type = type;
        this.balance = balance;
    }

    BankAccount(AccountType type) {
        this(type, 0);
    }

    void commitTransaction(int routingNumber, long transactionId, String customerId, double amount) {
        this.transactions.put(transactionId, new Transaction(routingNumber,
                Integer.parseInt(customerId), transactionId, amount));
        this.balance += amount;
    }

    public AccountType getType() {
        return type;
    }

    public double getBalance() {
        return balance;
    }

    Map<Long, Transaction> getTransactions() {
        return transactions;
    }

    @Override
    public String toString() {
        return "%s account balance = £%.2f".formatted(type.toString().toLowerCase(), balance);
    }
}
