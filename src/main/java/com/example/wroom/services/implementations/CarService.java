package com.example.wroom.services.implementations;

import com.example.wroom.exceptions.BranchNotFoundException;
import com.example.wroom.exceptions.CarNotFoundException;
import com.example.wroom.models.Booking;
import com.example.wroom.models.Branch;
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
    Branch findCarBy(String carRegistrationNumber) throws BranchNotFoundException;

    /**
     * To find all branches
     * @return List of branches
     */
    List<Branch> findAllBranches();

    /**
     * To update an existing branch
     * @param branch Branch
     */
    void updateBranch(Branch branch) throws BranchNotFoundException;

    /**
     * To delete a branch by its ID
     * @param id id of the branch
     */
    void deleteBranchById(UUID id) throws BranchNotFoundException;

    /**
     * To restore a branch by its ID
     * @param id
     */
    void restoreBranchById(UUID id) throws BranchNotFoundException;
}
