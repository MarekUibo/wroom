package com.example.wroom.services;

import com.example.wroom.exceptions.EmployeeNotFoundException;
import com.example.wroom.models.Employee;
import com.example.wroom.models.Person;

import java.util.List;
import java.util.UUID;

/**
 * To handle employee related operations
 *
 * @author Rigottier Jonathan
 */

public interface EmployeeService {
    /**
     * To create a new employee
     * @param employee Employee
     */
    void createEmployee(Employee employee);

    /**
     * To find an employee by its ID
     * @param id id of the employee
     * @return Employee
     */
    Employee findEmployeeById(UUID id) throws EmployeeNotFoundException;

    /**
     * To find an employee by its email
     * @param email Email of the employee
     * @return Employee
     */
    Employee findEmployeeByEmail(Person email) throws EmployeeNotFoundException;

    /**
     * To find all employees
     * @return List of employees
     */
    List<Employee> findAllEmployees();

    /**
     * To update an existing employee
     * @param employee Employee
     */
    void updateEmployee(Employee employee) throws EmployeeNotFoundException;

    /**
     * To delete an employee by its ID
     * @param id id of the employee
     */
    void deleteEmployeeById(UUID id) throws EmployeeNotFoundException;

    /**
     * To restore an employee by its ID
     * @param id
     */
    void restoreEmployeeById(UUID id) throws EmployeeNotFoundException;
}