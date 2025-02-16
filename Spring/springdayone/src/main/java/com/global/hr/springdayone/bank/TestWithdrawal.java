package com.global.hr.springdayone.bank;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestWithdrawal {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        WithdrawalService withdrawalService = (WithdrawalService) context.getBean("withdrawalService");

        withdrawalService.checkBalance();
        withdrawalService.depositAmount(500);
        withdrawalService.withdrawAmount(300);
        withdrawalService.withdrawAmount(1500);
        withdrawalService.checkBalance();
    }
}
