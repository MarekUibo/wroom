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
import java.util.Optional;
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
        car.setActive(true);
        carRepository.save(car);
    }

    @Override
    public Car findCarById(UUID id) throws CarNotFoundException {
        Optional<Car> optionalCar = carRepository.findById(id);

        if(optionalCar.isEmpty()) {
            throw new CarNotFoundException(id);
        }
        return optionalCar.get();
    }

    @Override
    public Car findCarByCarRegistrationNumber(String carRegistrationNumber) throws CarNotFoundException {
        Optional<Car> optionalCar = carRepository.findByCarRegistrationNumber(carRegistrationNumber);

        if(optionalCar.isEmpty()) {
            throw new CarNotFoundException(carRegistrationNumber);
        }
        return optionalCar.get();
    }

    @Override
    public List<Car> findAllCars() {
        return carRepository.findAll();
    }

    @Override
    public void updateCar(Car car) throws CarNotFoundException {
        if(findCarById(car.getId()) != null) {
            carRepository.saveAndFlush(car);
        }
    }

    @Override
    public void deleteCarById(UUID id) throws CarNotFoundException {
        Car car = findCarById(id);
        car.setActive(false);
        carRepository.saveAndFlush(car);
    }

    @Override
    public void restoreCarById(UUID id) throws CarNotFoundException {
        Car car = findCarById(id);
        car.setActive(true);
        carRepository.saveAndFlush(car);
    }
}
