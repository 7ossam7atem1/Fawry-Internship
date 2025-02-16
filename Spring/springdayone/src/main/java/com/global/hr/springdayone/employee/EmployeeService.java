package com.global.hr.springdayone.employee;

import com.global.hr.springdayone.employee.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeService {
    private final List<Employee> employees = new ArrayList<>();

    // Create Employee
    public void createEmployee(int id, String name, String position) {
        Employee employee = new Employee(id, name, position);
        employees.add(employee);
        System.out.println("Employee added: " + employee);
    }

    // Update Employee
    public void updateEmployee(int id, String newName, String newPosition) {
        Optional<Employee> employeeToUpdate = employees.stream()
                .filter(emp -> emp.getId() == id)
                .findFirst();

        if (employeeToUpdate.isPresent()) {
            Employee employee = employeeToUpdate.get();
            employee.setName(newName);
            employee.setPosition(newPosition);
            System.out.println("Employee updated: " + employee);
        } else {
            System.out.println("Employee with ID " + id + " not found.");
        }
    }

    // Delete Employee
    public void deleteEmployee(int id) {
        Optional<Employee> employeeToDelete = employees.stream()
                .filter(emp -> emp.getId() == id)
                .findFirst();

        if (employeeToDelete.isPresent()) {
            employees.remove(employeeToDelete.get());
            System.out.println("Employee removed: " + employeeToDelete.get());
        } else {
            System.out.println("Employee with ID " + id + " not found.");
        }
    }

    // List Employees
    public void listEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
        } else {
            System.out.println("Employee List:");
            employees.forEach(System.out::println);
        }
    }
}
