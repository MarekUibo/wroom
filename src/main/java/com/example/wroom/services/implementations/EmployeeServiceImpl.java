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
import java.util.Optional;
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
        employee.setActive(true);
        employeeRepository.save(employee);
    }

    @Override
    public Employee findEmployeeById(UUID id) throws EmployeeNotFoundException {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);

        if(optionalEmployee.isEmpty()) {
            throw new EmployeeNotFoundException(id);
        }
        return optionalEmployee.get();
    }

    @Override
    public Employee findEmployeeByEmail(Person email) throws EmployeeNotFoundException {
        Optional<Employee> optionalEmployee = employeeRepository.findByEmployeeEmail(email);

        if(optionalEmployee.isEmpty()) {
            throw new EmployeeNotFoundException(email);
        }
        return optionalEmployee.get();
    }

    @Override
    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public void updateEmployee(Employee employee) throws EmployeeNotFoundException {
        if(findEmployeeById(employee.getId()) != null) {
            employeeRepository.saveAndFlush(employee);
        }
    }

    @Override
    public void deleteEmployeeById(UUID id) throws EmployeeNotFoundException {
        Employee employee = findEmployeeById(id);
        employee.setActive(false);
        employeeRepository.saveAndFlush(employee);
    }

    @Override
    public void restoreEmployeeById(UUID id) throws EmployeeNotFoundException {
        Employee employee = findEmployeeById(id);
        employee.setActive(true);
        employeeRepository.saveAndFlush(employee);
    }
}
