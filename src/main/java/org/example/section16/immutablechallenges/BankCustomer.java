package org.example.section16.immutablechallenges;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankCustomer {
    private final String name;
    private static int idCounter = 0;
    private final int customerId;
    private final Map<AccountType, BankAccount> accounts = new HashMap<>();

    BankCustomer(String name, double checkingBalance, double savingsBalance) {
        this.name = name;
        this.customerId = idCounter++;
        this.accounts.put(AccountType.CHECKING, new BankAccount(AccountType.CHECKING, checkingBalance));
        this.accounts.put(AccountType.SAVINGS, new BankAccount(AccountType.SAVINGS, savingsBalance));
    }

    void doTransaction(AccountType type, int routingNumber, long transactionId, double amount) {
        if (accounts.get(type).getBalance() + amount < 0) return;
        accounts.get(type).commitTransaction(routingNumber, transactionId, this.getCustomerId(), amount);
    }

    public String getName() {
        return name;
    }

    public String getCustomerId() {
        return "%015d".formatted(customerId);
    }

    List<BankAccount> getAccounts() {
        return List.of(accounts.get(AccountType.CHECKING), accounts.get(AccountType.SAVINGS));
    }

    BankAccount getAccount(AccountType type) {
        return accounts.get(type);
    }

    @Override
    public String toString() {
        return """
                customer %s: %s
                %s
                %s""".formatted(this.getCustomerId(), name, accounts.get(AccountType.CHECKING), accounts.get(AccountType.SAVINGS));
    }
}
