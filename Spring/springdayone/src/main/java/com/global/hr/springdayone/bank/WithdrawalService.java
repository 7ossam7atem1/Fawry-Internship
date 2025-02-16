package com.global.hr.springdayone.bank;

public class WithdrawalService {
    private final BalanceService balanceService;

    public WithdrawalService(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    public void withdrawAmount(double amount) {
        System.out.println("Processing withdrawal...");
        balanceService.withdraw(amount);
    }

    public void depositAmount(double amount) {
        System.out.println("Processing deposit...");
        balanceService.deposit(amount);
    }

    public void checkBalance() {
        System.out.println("Checking balance...");
        balanceService.checkBalance();
    }
}
