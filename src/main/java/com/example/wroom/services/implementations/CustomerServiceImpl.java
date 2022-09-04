package com.example.wroom.services.implementations;

import com.example.wroom.exceptions.CustomerNotFoundException;
import com.example.wroom.models.Customer;
import com.example.wroom.models.Person;
import com.example.wroom.repository.BookingRepository;
import com.example.wroom.repository.CustomerRepository;
import com.example.wroom.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Implementation of customer Service
 *
 * @author Jonathan Rigottier
 */
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void createCustomer(Customer customer) {
        customer.setActive(true);
        customerRepository.save(customer);
    }

    @Override
    public Customer findCustomerById(UUID id) throws CustomerNotFoundException {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        if(optionalCustomer.isEmpty()) {
            throw new CustomerNotFoundException(id);
        }
        return optionalCustomer.get();
    }

    @Override
    public Customer findCustomerByEmail(Person email) throws CustomerNotFoundException {
        Optional<Customer> optionalCustomer = customerRepository.findByCustomerEmail(email);

        if(optionalCustomer.isEmpty()) {
            throw new CustomerNotFoundException(String.valueOf(email));
        }
        return optionalCustomer.get();
    }

    @Override
    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public void updateCustomer(Customer customer) throws CustomerNotFoundException {
        if(findCustomerById(customer.getId()) != null) {
            customerRepository.saveAndFlush(customer);
        }
    }

    @Override
    public void deleteCustomerById(UUID id) throws CustomerNotFoundException {
        Customer customer = findCustomerById(id);
        customer.setActive(false);
        customerRepository.saveAndFlush(customer);
    }

    @Override
    public void restoreCustomerById(UUID id) throws CustomerNotFoundException {
        Customer customer = findCustomerById(id);
        customer.setActive(true);
        customerRepository.saveAndFlush(customer);
    }
}
