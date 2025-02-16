package com.global.hr.springdayone;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class CustomerAccount {
    private String accountId;
    private String customerName;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public void createAccount() {
        System.out.println("Account created for " + customerName + " with ID: " + accountId);
    }

    @PostConstruct
    public void init() throws Exception{
        System.out.println("Bean is going through init.");
    }
    @PreDestroy
    public void destroy() throws Exception{
        System.out.println("Bean is going through destroying");
    }

    @Override
    public String toString() {
        return "CustomerAccount{" +
                "accountId='" + accountId + '\'' +
                ", customerName='" + customerName + '\'' +
                '}';
    }


}
