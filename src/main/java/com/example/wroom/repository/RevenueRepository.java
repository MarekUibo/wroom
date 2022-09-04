package com.example.wroom.repository;

import com.example.wroom.models.Revenue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repository to handle revenue related DB operations
 *
 * @author: Marek Uibo & Jonathan Rigottier
 */
@Repository
public interface RevenueRepository extends JpaRepository<Revenue, UUID> {

}

