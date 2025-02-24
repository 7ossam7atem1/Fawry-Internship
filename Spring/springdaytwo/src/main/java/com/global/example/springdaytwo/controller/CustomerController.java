package com.global.example.springdaytwo.controller;

import com.global.example.springdaytwo.entities.Customer;
import com.global.example.springdaytwo.repository.CustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//
//@RestController
//@RequestMapping("/api/customers")
//public class CustomerController {
//
//    private final CustomerRepository customerRepository;
//
//    public CustomerController(CustomerRepository customerRepository) {
//        this.customerRepository = customerRepository;
//    }
//
//
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public Customer createCustomer(@RequestBody Customer customer) {
//        return customerRepository.save(customer);
//    }
//
//
//    @GetMapping
//    public List<Customer> getAllCustomers() {
//        return customerRepository.findAll();
//    }
//
//
//    @GetMapping("/{id}")
//    public Customer getCustomerById(@PathVariable Long id) {
//        return customerRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Customer not found"));
//    }
//
//
//    @PutMapping("/{id}")
//    public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer customerDetails) {
//        return customerRepository.findById(id)
//                .map(customer -> {
//                    customer.setFirstName(customerDetails.getFirstName());
//                    customer.setLastName(customerDetails.getLastName());
//                    customer.setEmail(customerDetails.getEmail());
//                    return customerRepository.save(customer);
//                })
//                .orElseThrow(() -> new RuntimeException("Customer not found"));
//    }
//
//
//    @DeleteMapping("/{id}")
//    public void deleteCustomer(@PathVariable Long id) {
//        customerRepository.deleteById(id);
//    }
//
//
//    @GetMapping("/search/email/{email}")
//    public ResponseEntity<Customer> searchByEmail(@PathVariable String email) {
//        Customer customer = customerRepository.findByEmail(email);
//        if (customer != null) {
//            return ResponseEntity.ok(customer);
//        }
//        return ResponseEntity.notFound().build();
//    }
//
//
//    @GetMapping("/search/name/{name}")
//    public ResponseEntity<List<Customer>> searchByName(@PathVariable String name) {
//        List<Customer> customers = customerRepository.findByName(name);
//        return ResponseEntity.ok(customers);
//    }
//
//
//    @GetMapping("/search/firstName/{firstName}")
//    public ResponseEntity<List<Customer>> searchByFirstName(@PathVariable String firstName) {
//        List<Customer> customers = customerRepository.findByFirstNameContainingIgnoreCase(firstName);
//        return ResponseEntity.ok(customers);
//    }
//
//
//    @GetMapping("/search/lastName/{lastName}")
//    public ResponseEntity<List<Customer>> searchByLastName(@PathVariable String lastName) {
//        List<Customer> customers = customerRepository.findByLastNameContainingIgnoreCase(lastName);
//        return ResponseEntity.ok(customers);
//    }
//
//
//    @GetMapping("/search")
//    public ResponseEntity<List<Customer>> searchCustomers(
//            @RequestParam(required = false) String firstName,
//            @RequestParam(required = false) String lastName,
//            @RequestParam(required = false) String email) {
//
//        List<Customer> customers = customerRepository.searchCustomers(firstName, lastName, email);
//        return ResponseEntity.ok(customers);
//    }
//}

//pagination


import com.global.example.springdaytwo.entities.Customer;
import com.global.example.springdaytwo.entities.PageResponse;
import com.global.example.springdaytwo.projection.CustomerFullNameProjection;
import com.global.example.springdaytwo.projection.CustomerNameDTO;
import com.global.example.springdaytwo.projection.CustomerProjection;
import com.global.example.springdaytwo.repository.CustomerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    @GetMapping
    public ResponseEntity<PageResponse<Customer>> getAllCustomers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {

        Sort sort = createSort(sortBy, sortDir);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Customer> customerPage = customerRepository.findAll(pageable);

        return ResponseEntity.ok(new PageResponse<>(customerPage));
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer customerDetails) {
        return customerRepository.findById(id)
                .map(customer -> {
                    customer.setFirstName(customerDetails.getFirstName());
                    customer.setLastName(customerDetails.getLastName());
                    customer.setEmail(customerDetails.getEmail());
                    return customerRepository.save(customer);
                })
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        if (!customerRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        customerRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search/email/{email}")
    public ResponseEntity<PageResponse<Customer>> searchByEmail(
            @PathVariable String email,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {

        Sort sort = createSort(sortBy, sortDir);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Customer> customerPage = customerRepository.findByEmailContainingIgnoreCase(email, pageable);

        return ResponseEntity.ok(new PageResponse<>(customerPage));
    }

    @GetMapping("/search/name/{name}")
    public ResponseEntity<PageResponse<Customer>> searchByName(
            @PathVariable String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {

        Sort sort = createSort(sortBy, sortDir);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Customer> customerPage = customerRepository.findByName(name, pageable);

        return ResponseEntity.ok(new PageResponse<>(customerPage));
    }

    @GetMapping("/search/firstName/{firstName}")
    public ResponseEntity<PageResponse<Customer>> searchByFirstName(
            @PathVariable String firstName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {

        Sort sort = createSort(sortBy, sortDir);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Customer> customerPage = customerRepository.findByFirstNameContainingIgnoreCase(firstName, pageable);

        return ResponseEntity.ok(new PageResponse<>(customerPage));
    }

    @GetMapping("/search/lastName/{lastName}")
    public ResponseEntity<PageResponse<Customer>> searchByLastName(
            @PathVariable String lastName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {

        Sort sort = createSort(sortBy, sortDir);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Customer> customerPage = customerRepository.findByLastNameContainingIgnoreCase(lastName, pageable);

        return ResponseEntity.ok(new PageResponse<>(customerPage));
    }

    @GetMapping("/search")
    public ResponseEntity<PageResponse<Customer>> searchCustomers(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String email,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {

        Sort sort = createSort(sortBy, sortDir);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Customer> customerPage = customerRepository.searchCustomers(firstName, lastName, email, pageable);

        return ResponseEntity.ok(new PageResponse<>(customerPage));
    }

    private Sort createSort(String sortBy, String sortDir) {
        validateSortField(sortBy);
        return sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
    }

    private void validateSortField(String sortBy) {
        List<String> allowedFields = Arrays.asList("id", "firstName", "lastName", "email", "createdAt");
        if (!allowedFields.contains(sortBy)) {
            throw new IllegalArgumentException("Invalid sort field: " + sortBy);
        }
    }
    @GetMapping("/test/emails")
    public ResponseEntity<List<String>> getAllEmails() {
        List<String> emails = customerRepository.findAll()
                .stream()
                .map(Customer::getEmail)
                .collect(Collectors.toList());
        return ResponseEntity.ok(emails);
    }

    @GetMapping("/projected")
    public ResponseEntity<PageResponse<CustomerProjection>> getAllCustomersProjected(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {

        Sort sort = createSort(sortBy, sortDir);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<CustomerProjection> customerPage = customerRepository.findAllProjectedBy(pageable);

        return ResponseEntity.ok(new PageResponse<>(customerPage));
    }

    @GetMapping("/projected/{id}")
    public ResponseEntity<CustomerProjection> getCustomerProjectedById(@PathVariable Long id) {
        CustomerProjection customer = customerRepository.findProjectedById(id);
        if (customer != null) {
            return ResponseEntity.ok(customer);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/projected/search/email/{email}")
    public ResponseEntity<PageResponse<CustomerProjection>> searchByEmailProjected(
            @PathVariable String email,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {

        Sort sort = createSort(sortBy, sortDir);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<CustomerProjection> customerPage = customerRepository.findProjectedByEmailContaining(email, pageable);

        return ResponseEntity.ok(new PageResponse<>(customerPage));
    }

    @GetMapping("/projected/search/name/{name}")
    public ResponseEntity<PageResponse<CustomerProjection>> searchByNameProjected(
            @PathVariable String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {

        Sort sort = createSort(sortBy, sortDir);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<CustomerProjection> customerPage = customerRepository.findProjectedByName(name, pageable);

        return ResponseEntity.ok(new PageResponse<>(customerPage));
    }

    @GetMapping("/projected/search")
    public ResponseEntity<PageResponse<CustomerProjection>> searchCustomersProjected(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String email,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {

        Sort sort = createSort(sortBy, sortDir);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<CustomerProjection> customerPage = customerRepository.searchCustomersProjected(
                firstName, lastName, email, pageable);

        return ResponseEntity.ok(new PageResponse<>(customerPage));
    }

    @GetMapping("/fullnames")
    public ResponseEntity<List<CustomerFullNameProjection>> getAllCustomerFullNames() {
        return ResponseEntity.ok(customerRepository.findAllProjectedBy());
    }

    @GetMapping("/names/all")
    public ResponseEntity<List<CustomerNameDTO>> getAllCustomerNames() {
        return ResponseEntity.ok(customerRepository.findAllFullNames());
    }
}

