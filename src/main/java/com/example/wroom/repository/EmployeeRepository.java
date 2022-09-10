package com.example.wroom.repository;

import com.example.wroom.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Repository to handle employee related DB operations
 *
 * @author: Marek Uibo & Jonathan Rigottier
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {

    Optional<Employee> findByEmail(String email);
}

