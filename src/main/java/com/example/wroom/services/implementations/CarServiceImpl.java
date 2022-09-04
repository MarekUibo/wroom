package com.example.wroom.services.implementations;

import com.example.wroom.exceptions.CarNotFoundException;
import com.example.wroom.models.Car;
import com.example.wroom.repository.BookingRepository;
import com.example.wroom.repository.CarRepository;
import com.example.wroom.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

/**
 * Implementation of Car Service
 *
 * @author Jonathan Rigottier
 */
@Service
@Transactional
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Override
    public void createCar(Car car) {

    }

    @Override
    public Car findCarById(UUID id) throws CarNotFoundException {
        return null;
    }

    @Override
    public Car findCarByCarRegistrationNumber(String carRegistrationNumber) throws CarNotFoundException {
        return null;
    }

    @Override
    public List<Car> findAllCars() {
        return null;
    }

    @Override
    public void updateCar(Car car) throws CarNotFoundException {

    }

    @Override
    public void deleteCarById(UUID id) throws CarNotFoundException {

    }

    @Override
    public void restoreCarById(UUID id) throws CarNotFoundException {

    }
}
