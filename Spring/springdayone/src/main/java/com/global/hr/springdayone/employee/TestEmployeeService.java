package com.global.hr.springdayone.employee;

import com.global.hr.springdayone.employee.EmployeeService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestEmployeeService {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        EmployeeService employeeService = (EmployeeService) context.getBean("employeeService");


        employeeService.createEmployee(1, "Hossam", "Developer");
        employeeService.createEmployee(2, "Hatem", "Manager");

        employeeService.listEmployees();

        employeeService.updateEmployee(2, "Bob Marley", "Senior Manager");
        employeeService.listEmployees();

        employeeService.deleteEmployee(1);


        employeeService.listEmployees();
    }
}
