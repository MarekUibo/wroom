package com.example.wroom.repository;
/**
 * @author:Marek Uibo
 */
import com.example.wroom.models.Revenue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RevenueRepository extends JpaRepository<Revenue, Long> {
}

