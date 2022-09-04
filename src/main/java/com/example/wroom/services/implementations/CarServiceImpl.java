package com.example.wroom.services.implementations;

import com.example.wroom.exceptions.CarNotFoundException;
import com.example.wroom.models.Car;
import com.example.wroom.services.CarService;

import java.util.List;
import java.util.UUID;

public class CarServiceImpl implements CarService {
    @Override
    public void createCar(Car car) {

    }

    @Override
    public Car findCarById(UUID id) throws CarNotFoundException {
        return null;
    }

    @Override
    public Car findCarBy(String carRegistrationNumber) throws CarNotFoundException {
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
