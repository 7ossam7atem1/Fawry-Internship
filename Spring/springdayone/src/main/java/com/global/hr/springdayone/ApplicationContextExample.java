package com.global.hr.springdayone;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextExample {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        CustomerAccount account = (CustomerAccount) context.getBean("customerAccount");
        account.createAccount();
    }
}