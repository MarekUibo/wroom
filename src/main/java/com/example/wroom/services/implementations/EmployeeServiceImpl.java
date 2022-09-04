package com.example.wroom.services.implementations;

import com.example.wroom.exceptions.EmployeeNotFoundException;
import com.example.wroom.models.Employee;
import com.example.wroom.models.Person;
import com.example.wroom.services.EmployeeService;

import java.util.List;
import java.util.UUID;

public class EmployeeServiceImpl implements EmployeeService {
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
