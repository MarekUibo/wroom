package com.example.wroom.repository;
/**
 * @author:Marek Uibo
 */
import com.example.wroom.models.RentalOffice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalOfficeRepository extends JpaRepository<RentalOffice, Long> {
}

