package com.example.wroom.compnents;

import com.example.wroom.exceptions.BranchNotFoundException;
import com.example.wroom.exceptions.CarNotFoundException;
import com.example.wroom.exceptions.EmployeeNotFoundException;
import com.example.wroom.exceptions.RentalOfficeNotFoundException;
import com.example.wroom.models.*;
import com.example.wroom.services.BranchService;
import com.example.wroom.services.CarService;
import com.example.wroom.services.EmployeeService;
import com.example.wroom.services.RentalOfficeService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * @author Marek Uibo
 */

public class DataInit {

    @Autowired
    private BranchService branchService;

    @Autowired
    private RentalOfficeService rentalOfficeService;

    @Autowired
    private CarService carService;

    @Autowired
    private EmployeeService employeeService;

    @PostConstruct
    public void init() {
        initBranch();
        initCar();
        initEmployee();
        initRentalOffice();
    }

    private void initBranch() {
        System.out.println("Starting initializing Booking..");
        Branch branch = new Branch();
        branch.setName("Tallinn autorent");
        branch.setFullAddress("27 Parnu mnt, Tallinn");
        branch.setPhoneNumber("123456789");
        branch.setEmail("tallinn@autorent.ee");
        branch.setCity("Tallinn");


        try {
            Branch searchBranch = branchService.findBranchById(branch.getId());
            System.out.println("Cannot pre-initialize Booking: " + searchBranch.getId());
        } catch (BranchNotFoundException e) {
            branchService.createBranch(branch);
        }
    }

    private void initCar() {
        System.out.println("Starting initializing Car..");

        try {
            Car car = new Car();
            car.setRegistrationNumber("123ABC");
            car.setMark("Audi");
            car.setModel("A6");
            car.setBodyType(CarBodyType.SEDAN);
            car.setYearOfProduction(2019);
            car.setColor("Black");
            car.setMileage(10000L);
            car.setHomeBranch(branchService.findRandomActiveBranch());

            try {
                Car searchCar = carService.findCarByRegistrationNumber(car.getRegistrationNumber());
                System.out.println("Cannot pre-initialize Car: " + searchCar);
            } catch (CarNotFoundException e) {
                carService.createCar(car);
            }
        } catch (BranchNotFoundException e) {
            System.out.println("No branch found. Cannot pre-initialize Car: " + e.getMessage());
        }
    }

    private void initEmployee() {
        System.out.println("Starting initializing Employee..");

        try {
            Employee employee = new Employee();
            employee.setFirstName("John");
            employee.setLastName("Doe");
            employee.setAddress("address");
            employee.setPhoneNumber("123456789");
            employee.setRole(Role.MANAGER);
            employee.setHomeBranch(branchService.findRandomActiveBranch());

            try {
                Employee searchEmployee = employeeService.findEmployeeById(employee.getId());
                System.out.println("Cannot pre-initialize Employee: " + searchEmployee);
            } catch (EmployeeNotFoundException e) {
                employeeService.createEmployee(employee);
            }
        } catch (BranchNotFoundException e) {
            System.out.println("No branch found. Cannot pre-initialize Employee: " + e.getMessage());
        }
    }

    private void initRentalOffice() {
        System.out.println("Starting initializing RentalOffice..");
        RentalOffice rentalOffice = new RentalOffice();
        rentalOffice.setName("Tallinn");
        rentalOffice.setContactAddress("address");

        try {
            RentalOffice searchRentalOffice = rentalOfficeService.findRentalOfficeById(rentalOffice.getId());
            System.out.println("Cannot pre-initialize RentalOffice: " + searchRentalOffice);
        } catch (RentalOfficeNotFoundException e) {
            rentalOfficeService.createRentalOffice(rentalOffice);
        }
    }
}

