package com.example.wroom.services;

import com.example.wroom.exceptions.CarNotFoundException;
import com.example.wroom.models.Car;

import java.util.List;
import java.util.UUID;

/**
 * To handle car related operations
 *
 * @author Rigottier Jonathan
 */
public interface CarService {
    /**
     * To create a new car
     * @param car Car
     */
    void createCar(Car car);

    /**
     * To find a car by its ID
     * @param id id of the car
     * @return Car
     */
    Car findCarById(UUID id) throws CarNotFoundException;

    /**
     * To find a car by its car registration number
     * @param carRegistrationNumber CarRegistrationNumber of the car
     * @return Car
     */
    Car findCarBy(String carRegistrationNumber) throws CarNotFoundException;

    /**
     * To find all cars
     * @return List of cars
     */
    List<Car> findAllCars();

    /**
     * To update an existing car
     * @param car Car
     */
    void updateCar(Car car) throws CarNotFoundException;

    /**
     * To delete a car by its ID
     * @param id id of the car
     */
    void deleteCarById(UUID id) throws CarNotFoundException;

    /**
     * To restore a car by its ID
     * @param id
     */
    void restoreCarById(UUID id) throws CarNotFoundException;

}
