package com.global.hr.springdayone;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanLifeCycleExample {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        CustomerAccount account = (CustomerAccount) context.getBean("customerAccount");
        account.createAccount();

        context.close();
        System.out.println("Bean destroyed");
    }
}