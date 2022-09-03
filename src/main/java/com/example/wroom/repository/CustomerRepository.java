package com.example.wroom.repository;
/**
 * @author:Marek Uibo
 */
import com.example.wroom.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByEmail(String email);
}

