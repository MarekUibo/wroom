package com.example.wroom.services.implementations;

import com.example.wroom.exceptions.EmployeeNotFoundException;
import com.example.wroom.models.Employee;
import com.example.wroom.models.Person;
import com.example.wroom.repository.BookingRepository;
import com.example.wroom.repository.EmployeeRepository;
import com.example.wroom.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

/**
 * Implementation of Employee Service
 *
 * @author Jonathan Rigottier
 */
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void createEmployee(Employee employee) {

    }

    @Override
    public Employee findEmployeeById(UUID id) throws EmployeeNotFoundException {
        return null;
    }

    @Override
    public Employee findEmployeeByAddress(Person email) throws EmployeeNotFoundException {
        return null;
    }

    @Override
    public List<Employee> findAllEmployees() {
        return null;
    }

    @Override
    public void updateEmployee(Employee employee) throws EmployeeNotFoundException {

    }

    @Override
    public void deleteEmployeeById(UUID id) throws EmployeeNotFoundException {

    }

    @Override
    public void restoreEmployeeById(UUID id) throws EmployeeNotFoundException {

    }
}
