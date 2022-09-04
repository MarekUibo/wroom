package com.example.wroom.repository;
/**
 * @author:Marek Uibo
 */
import com.example.wroom.models.RentalStart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalStartRepository extends JpaRepository<RentalStart, Long> {
}

