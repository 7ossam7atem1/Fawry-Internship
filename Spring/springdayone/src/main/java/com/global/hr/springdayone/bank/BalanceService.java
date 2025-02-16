package com.global.hr.springdayone.bank;

public class BalanceService {
    private double balance;

    public BalanceService(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited: $" + amount + ". New balance: $" + balance);
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: $" + amount + ". Remaining balance: $" + balance);
        } else {
            System.out.println("Insufficient funds! Available balance: $" + balance);
        }
    }

    public void checkBalance() {
        System.out.println("Current Balance: $" + balance);
    }
}
