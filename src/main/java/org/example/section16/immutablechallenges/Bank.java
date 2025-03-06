package org.example.section16.immutablechallenges;

import java.util.HashMap;
import java.util.Map;

public class Bank {
    private final int routingNumber;
    private long lastTransactionId;
    private Map<String, BankCustomer> customers = new HashMap<>();

    public Bank(int routingNumber) {
        this.routingNumber = routingNumber;
        this.lastTransactionId = 0;
    }

    public BankCustomer getCustomer(String id) {
        if (!customers.containsKey(id)) return null;
        return customers.get(id);
    }

    public void addCustomer(String name, double checkingInitialDeposit, double savingsInitialDeposit) {
        BankCustomer customer = new BankCustomer(name, checkingInitialDeposit, savingsInitialDeposit);
        customers.put(customer.getCustomerId(), customer);
    }

    public void doTransaction(String id, AccountType type, double amount) {
        BankCustomer customer = customers.get(id);
        if (customer == null) return;
        customer.doTransaction(type, routingNumber, lastTransactionId++, amount);
    }

    public void printTransactions(String customerId, AccountType type) {
        System.out.println(customers.get(customerId).getAccount(type).getTransactions());
    }

    public void printCustomers() {
        customers.values().forEach(customer -> System.out.println(customer + "\n"));
    }
}
