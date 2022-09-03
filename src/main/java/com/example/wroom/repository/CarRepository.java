package com.example.wroom.repository;
/**
 * @author:Marek Uibo
 */
import com.example.wroom.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    Car findByLicensePlate(String licensePlate);
}

