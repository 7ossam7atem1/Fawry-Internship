//package com.global.example.springdaytwo.repository;
//
//import com.global.example.springdaytwo.entities.Customer;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//import java.util.List;
//
//public interface CustomerRepository extends JpaRepository<Customer, Long> {
//
//
//    Customer findByEmail(String email);
//
//    @Query("SELECT c FROM Customer c WHERE " +
//            "LOWER(c.firstName) LIKE LOWER(CONCAT('%', :name, '%')) OR " +
//            "LOWER(c.lastName) LIKE LOWER(CONCAT('%', :name, '%'))")
//    List<Customer> findByName(@Param("name") String name);
//
//    List<Customer> findByFirstNameContainingIgnoreCase(String firstName);
//
//    List<Customer> findByLastNameContainingIgnoreCase(String lastName);
//
//    @Query("SELECT c FROM Customer c WHERE " +
//            "(:firstName IS NULL OR LOWER(c.firstName) LIKE LOWER(CONCAT('%', :firstName, '%'))) AND " +
//            "(:lastName IS NULL OR LOWER(c.lastName) LIKE LOWER(CONCAT('%', :lastName, '%'))) AND " +
//            "(:email IS NULL OR LOWER(c.email) LIKE LOWER(CONCAT('%', :email, '%')))")
//    List<Customer> searchCustomers(
//            @Param("firstName") String firstName,
//            @Param("lastName") String lastName,
//            @Param("email") String email);
//}

//pagination
//package com.global.example.springdaytwo.repository;
//
//import com.global.example.springdaytwo.entities.Customer;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//public interface CustomerRepository extends JpaRepository<Customer, Long> {
//
//    @Query("SELECT c FROM Customer c WHERE c.email LIKE %:email%")
//    Page<Customer> findByEmailContainingIgnoreCase(@Param("email") String email, Pageable pageable);
//
//
//    @Query("SELECT c FROM Customer c WHERE " +
//            "LOWER(c.firstName) LIKE LOWER(CONCAT('%', :name, '%')) OR " +
//            "LOWER(c.lastName) LIKE LOWER(CONCAT('%', :name, '%'))")
//    Page<Customer> findByName(@Param("name") String name, Pageable pageable);
//
//
//    Page<Customer> findByFirstNameContainingIgnoreCase(String firstName, Pageable pageable);
//
//
//    Page<Customer> findByLastNameContainingIgnoreCase(String lastName, Pageable pageable);
//
//
//    @Query("SELECT c FROM Customer c WHERE " +
//            "(:firstName IS NULL OR LOWER(c.firstName) LIKE LOWER(CONCAT('%', :firstName, '%'))) AND " +
//            "(:lastName IS NULL OR LOWER(c.lastName) LIKE LOWER(CONCAT('%', :lastName, '%'))) AND " +
//            "(:email IS NULL OR LOWER(c.email) LIKE LOWER(CONCAT('%', :email, '%')))")
//    Page<Customer> searchCustomers(
//            @Param("firstName") String firstName,
//            @Param("lastName") String lastName,
//            @Param("email") String email,
//            Pageable pageable);
//}

//projection:

package com.global.example.springdaytwo.repository;

import com.global.example.springdaytwo.entities.Customer;
import com.global.example.springdaytwo.projection.CustomerFullNameProjection;
import com.global.example.springdaytwo.projection.CustomerNameDTO;
import com.global.example.springdaytwo.projection.CustomerProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {


    @Query("SELECT c FROM Customer c WHERE c.email LIKE %:email%")
    Page<Customer> findByEmailContainingIgnoreCase(@Param("email") String email, Pageable pageable);

    @Query("SELECT c FROM Customer c WHERE " +
            "LOWER(c.firstName) LIKE LOWER(CONCAT('%', :name, '%')) OR " +
            "LOWER(c.lastName) LIKE LOWER(CONCAT('%', :name, '%'))")
    Page<Customer> findByName(@Param("name") String name, Pageable pageable);

    Page<Customer> findByFirstNameContainingIgnoreCase(String firstName, Pageable pageable);

    Page<Customer> findByLastNameContainingIgnoreCase(String lastName, Pageable pageable);

    @Query("SELECT c FROM Customer c WHERE " +
            "(:firstName IS NULL OR LOWER(c.firstName) LIKE LOWER(CONCAT('%', :firstName, '%'))) AND " +
            "(:lastName IS NULL OR LOWER(c.lastName) LIKE LOWER(CONCAT('%', :lastName, '%'))) AND " +
            "(:email IS NULL OR LOWER(c.email) LIKE LOWER(CONCAT('%', :email, '%')))")
    Page<Customer> searchCustomers(
            @Param("firstName") String firstName,
            @Param("lastName") String lastName,
            @Param("email") String email,
            Pageable pageable);

    //projection
    Page<CustomerProjection> findAllProjectedBy(Pageable pageable);

    CustomerProjection findProjectedById(Long id);

    @Query("SELECT c FROM Customer c WHERE c.email LIKE %:email%")
    Page<CustomerProjection> findProjectedByEmailContaining(@Param("email") String email, Pageable pageable);

    @Query("SELECT c FROM Customer c WHERE " +
            "LOWER(c.firstName) LIKE LOWER(CONCAT('%', :name, '%')) OR " +
            "LOWER(c.lastName) LIKE LOWER(CONCAT('%', :name, '%'))")
    Page<CustomerProjection> findProjectedByName(@Param("name") String name, Pageable pageable);

    Page<CustomerProjection> findProjectedByFirstNameContainingIgnoreCase(String firstName, Pageable pageable);

    Page<CustomerProjection> findProjectedByLastNameContainingIgnoreCase(String lastName, Pageable pageable);

    @Query("SELECT c FROM Customer c WHERE " +
            "(:firstName IS NULL OR LOWER(c.firstName) LIKE LOWER(CONCAT('%', :firstName, '%'))) AND " +
            "(:lastName IS NULL OR LOWER(c.lastName) LIKE LOWER(CONCAT('%', :lastName, '%'))) AND " +
            "(:email IS NULL OR LOWER(c.email) LIKE LOWER(CONCAT('%', :email, '%')))")
    Page<CustomerProjection> searchCustomersProjected(
            @Param("firstName") String firstName,
            @Param("lastName") String lastName,
            @Param("email") String email,
            Pageable pageable);

    // Find all with full name projection
    List<CustomerFullNameProjection> findAllProjectedBy();

    // Query for all customers' full names
    @Query("SELECT new com.global.example.springdaytwo.projection.CustomerNameDTO(CONCAT(c.firstName, ' ', c.lastName)) FROM Customer c")
    List<CustomerNameDTO> findAllFullNames();

}