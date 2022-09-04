package com.example.wroom.repository;
/**
 * @author:Marek Uibo
 */
import com.example.wroom.models.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {
}

