package org.example.section10.bank;

import java.util.ArrayList;

// 10/02/25 Section 10 Exercise 46
public class Bank {
    private String name;
    private ArrayList<Branch> branches;

    public Bank(String name) {
        this.name = name;
        this.branches = new ArrayList<>();
    }

    public boolean addBranch(String name) {
        if (findBranch(name) == null) {
            this.branches.add(new Branch(name));
            return true;
        } else {
            return false;
        }
    }

    public boolean addCustomer(String branchName, String customerName, double initialTransaction) {
        Branch branch = findBranch(branchName);
        if (branch == null) {
            return false;
        } else {
            return branch.newCustomer(customerName,initialTransaction);
        }
    }

    public boolean addCustomerTransaction(String branchName, String customerName, double transaction) {
        Branch branch = findBranch(branchName);
        if (branch == null) {
            return false;
        } else {
            return branch.addCustomerTransaction(customerName, transaction);
        }
    }

    private Branch findBranch(String name) {
        for (Branch branch : this.branches) {
            if (branch.getName().equals(name)) {
                return branch;
            }
        }
        return null;
    }

    public boolean listCustomers(String branchName, boolean printTransactions) {
        Branch branch = findBranch(branchName);
        if (branch == null) {
            return false;
        }
        System.out.println("Customer details for branch " + branch.getName());
        for (int i = 0; i < branch.getCustomers().size(); i++) {
            Customer customer = branch.getCustomers().get(i);
            System.out.printf("Customer: %s[%d]%n", customer.getName(), i+1);
            if (printTransactions) {
                System.out.println("Transactions");
                for (int j = 0; j < customer.getTransactions().size(); j++) {
                    System.out.printf("[%d] Amount %.2f%n", j+1, customer.getTransactions().get(j));
                }
            }
        }
        return true;
    }
}
