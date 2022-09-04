package com.example.wroom.repository;
/**
 * @author:Marek Uibo
 */
import com.example.wroom.models.RentalEnd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalEndRepository extends JpaRepository<RentalEnd, Long> {
}

