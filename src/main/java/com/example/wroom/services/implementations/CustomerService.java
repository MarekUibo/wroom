package com.example.wroom.services.implementations;

import com.example.wroom.exceptions.BranchNotFoundException;
import com.example.wroom.exceptions.CustomerNotFoundException;
import com.example.wroom.models.Branch;
import com.example.wroom.models.Customer;
import com.example.wroom.models.Person;

import java.util.List;
import java.util.UUID;

/**
 * To handle customer related operations
 *
 * @author Rigottier Jonathan
 */
public interface CustomerService {
    /**
     * To create a new customer
     * @param customer Customer
     */
    void createCustomer(Customer customer);

    /**
     * To find a customer by its ID
     * @param id id of the customer
     * @return Customer
     */
    Customer findCustomerById(UUID id) throws CustomerNotFoundException;

    /**
     * To find a customer by its email
     * @param email Email of the customer
     * @return Customer
     */
    Customer findCustomerByEmail(Person email) throws CustomerNotFoundException;

    /**
     * To find all customers
     * @return List of customers
     */
    List<Customer> findAllCustomers();

    /**
     * To update an existing customer
     * @param customer Customer
     */
    void updateCustomer(Customer customer) throws CustomerNotFoundException;

    /**
     * To delete a customer by its ID
     * @param id id of the customer
     */
    void deleteCustomerById(UUID id) throws CustomerNotFoundException;

    /**
     * To restore a customer by its ID
     * @param id
     */
    void restoreCustomerById(UUID id) throws CustomerNotFoundException;
}
