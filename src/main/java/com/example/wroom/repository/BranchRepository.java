package com.example.wroom.repository;

import com.example.wroom.models.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Repository to handle branch related DB operations
 *
 * @author Marek Uibo & Jonathan Rigottier
 */
@Repository
public interface BranchRepository extends JpaRepository<Branch, UUID> {

    Optional<Branch> findByFullAddress(String address);

    Optional<Branch> findFirstByIsActiveIsTrue();

    Optional<Branch> findBranchByName(String name);
}
