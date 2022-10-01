package com.example.wroom.components;

import com.example.wroom.exceptions.*;
import com.example.wroom.models.*;
import com.example.wroom.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDate;

import static com.example.wroom.utils.Constants.Security.*;

/**
 * @author Marek Uibo and Kristiina Lindre
 */
@Component
public class DataInit {

    @Autowired
    private BranchService branchService;

    @Autowired
    private RentalOfficeService rentalOfficeService;

    @Autowired
    private CarService carService;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private UserService userService;

    @Autowired
    private BookingService bookingService;

    @PostConstruct
    public void init() {
        initAuthority();
        initBranch();
        initCar();
        initRentalOffice();
        initUser();
        initBooking();
    }

    private void initUser() {
        System.out.println("Starting initializing User..");

        try {
            Authority authority = authorityService.findAuthorityByName(AUTHORITY_ADMIN);
            Branch branch = branchService.findBranchByName("Tallinn autorent");

            User user = new User();
            user.setUserName("admin22");
            user.setEmail("admin@wroom.com");
            user.setPassword("123456");
            user.setAuthority(authority);
            user.setHomeBranch(branch);

            try {
                User resultUser = userService.findUserByUserName(user.getUserName());
                System.out.println("Already exists ! Cannot pre-initialize user:" + resultUser.getUserName());
            } catch (UserNotFoundException e) {
                userService.createUser(user);
            }
        } catch (AuthorityNotFoundException | BranchNotFoundException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    private void initBranch() {
        System.out.println("Starting initializing Branch..");
        Branch branch = new Branch();
        branch.setName("Tallinn autorent");
        branch.setFullAddress("27 Parnu mnt, Tallinn");
        branch.setPhoneNumber("123456789");
        branch.setEmail("tallinn@autorent.ee");
        branch.setCity("Tallinn");


        try {
            Branch searchBranch = branchService.findBranchByFullAddress(branch.getFullAddress());
            System.out.println("Already exists ! Cannot pre-initialize Branch : " + searchBranch.getFullAddress());
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
                System.out.println("Already exists ! Cannot pre-initialize Car: " + searchCar);
            } catch (CarNotFoundException e) {
                carService.createCar(car);
            }
        } catch (BranchNotFoundException e) {
            System.out.println("No branch found. Cannot pre-initialize Car: " + e.getMessage());
        }
    }

    private void initRentalOffice() {
        System.out.println("Starting initializing RentalOffice..");

        try {
            User user = userService.findUserByUserName("admin22");

            RentalOffice rentalOffice = new RentalOffice();
            rentalOffice.setName("Tallinn");
            rentalOffice.setInternetDomain("http://www.wroom-rental-car.ee");
            rentalOffice.setLogoUrl("https://upww.screenrec.com/images/f_GubritL5psP3ITwR0klX2EZxa6j9V4ho.png");
            rentalOffice.setOwner(user);
            rentalOffice.setFullAddress("Talinn, Parnu mnt 27");
            rentalOffice.setEmail("123@gmail.com");
            rentalOffice.setPhoneNumber("123456789");
            rentalOffice.setCity("Tallinn");
            rentalOffice.setSumOfAmountsForCarRental(BigDecimal.ZERO);

            try {
                RentalOffice searchRentalOffice = rentalOfficeService.findRentalOfficeByName(rentalOffice.getName());
                System.out.println("Already exists ! Cannot pre-initialize RentalOffice: " + searchRentalOffice);
            } catch (RentalOfficeNotFoundException e) {
                rentalOfficeService.createRentalOffice(rentalOffice);
            }
        } catch (UserNotFoundException e) {
            System.out.println("No user found. Cannot pre-initialize user: " + e.getMessage());
        }
    }

    private void initBooking() {
        System.out.println("Starting initializing Booking...");

        try {
            User user = userService.findUserByUserName("admin22");
            Car car = carService.findCarByRegistrationNumber("123ABC");
            Branch startBranch = branchService.findBranchByName("Tallinn autorent");
            Branch returnBranch = branchService.findBranchByName("Tallinn autorent");

            Booking booking = new Booking();
            booking.setUser(user);
            booking.setCar(car);
            booking.setDateFrom(LocalDate.parse("2022-09-25"));
            booking.setDateTo(LocalDate.parse("2022-09-30"));

            booking.setAdditionalPayment(BigDecimal.ZERO);
            booking.setComments("Test booking");
            booking.setAmount(BigDecimal.valueOf(199.99));
            booking.setRentalBranch(startBranch);
            booking.setReturnBranch(returnBranch);

            try {
                Booking resultBooking = bookingService.findBookingByUser(booking.getUser());
                System.out.println("Already exists ! Cannot pre-initialize booking :" + resultBooking.getUser());
            } catch (BookingNotFoundException e) {
                bookingService.createBooking(booking);
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }


    private void initAuthority() {
        System.out.println("Starting initializing Authority..");
        Authority authorityAdmin = new Authority();
        authorityAdmin.setName(AUTHORITY_ADMIN);
        createAuthority(authorityAdmin);

        Authority authorityOwner = new Authority();
        authorityOwner.setName(AUTHORITY_EMPLOYEE_OWNER);
        createAuthority(authorityOwner);

        Authority authorityManager = new Authority();
        authorityManager.setName(AUTHORITY_EMPLOYEE_MANAGER);
        createAuthority(authorityManager);

        Authority authorityEmployee = new Authority();
        authorityEmployee.setName(AUTHORITY_EMPLOYEE_SALES_PERSON);
        createAuthority(authorityEmployee);

        Authority authorityCustomer = new Authority();
        authorityCustomer.setName(AUTHORITY_CUSTOMER);
        createAuthority(authorityCustomer);
    }

    private void createAuthority(Authority authority) {
        try {
            Authority resultAuthority = authorityService.findAuthorityByName(authority.getName());
            System.out.println("Already created. Cannot pre-initialize authority:" + resultAuthority.getName());
        } catch (AuthorityNotFoundException authorityNotFoundException) {
            authorityService.createAuthority(authority);
        }
    }

}