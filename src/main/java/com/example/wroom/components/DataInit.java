package com.example.wroom.components;

import com.example.wroom.exceptions.*;
import com.example.wroom.models.*;
import com.example.wroom.services.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

import static com.example.wroom.utils.Constants.Security.*;

/**
 * @author Marek Uibo and Kristiina Lindre
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

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private SiteUserService siteUserService;

    @PostConstruct
    public void init() {
        initBranch();
        initCar();
        initEmployee();
        initRentalOffice();
        initSiteUser();
    }

    private void initSiteUser() {
        System.out.println("Starting initializing User..");

        try {
            Authority authority = authorityService.findAuthorityByName(AUTHORITY_ADMIN);

            User user = new User();
            user.setUserName("admin@wroom.com");
            user.setPassword("123456");
            user.setAuthority(authority);

            try {
                User resultUser = siteUserService.findUserByUserName(user.getUserName());
                System.out.println("Cannot pre-initialize user:" + resultUser.getUserName());
            } catch (UserNotFoundException e) {
                siteUserService.createUser(user);
            }
        } catch (AuthorityNotFoundException e) {
            System.out.println(e.getLocalizedMessage());
        }
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
    private void initAuthorityData() {
        System.out.println("Starting initializing Authority..");
        Authority authorityAdmin = new Authority();
        authorityAdmin.setName(AUTHORITY_ADMIN);
        createAuthority(authorityAdmin);

        Authority authorityManager = new Authority();
        authorityManager.setName(AUTHORITY_MANAGER);
        createAuthority(authorityManager);

        Authority authorityEmployee = new Authority();
        authorityEmployee.setName(AUTHORITY_EMPLOYEE);
        createAuthority(authorityEmployee);

        Authority authorityCustomer = new Authority();
        authorityCustomer.setName(AUTHORITY_CUSTOMER);
        createAuthority(authorityCustomer);
    }
    private void createAuthority(Authority authority) {
        try {
            Authority resultAuthority = authorityService.findAuthorityByName(authority.getName());
            System.out.println("Cannot pre-initialize authority:" + resultAuthority.getName());
        } catch(AuthorityNotFoundException authorityNotFoundException) {
            authorityService.createAuthority(authority);
        }
    }
}

