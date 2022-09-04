package com.example.wroom.repository;

import com.example.wroom.models.Customer;
import com.example.wroom.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Repository to handle customer related DB operations
 *
 * @author: Marek Uibo & Jonathan Rigottier
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    Optional<Customer> findByCustomerEmail(Person email);
}

