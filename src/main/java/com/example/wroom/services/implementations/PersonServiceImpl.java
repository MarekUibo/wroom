package com.example.wroom.services.implementations;

import com.example.wroom.exceptions.PersonNotFoundException;
import com.example.wroom.models.Person;
import com.example.wroom.repository.BookingRepository;
import com.example.wroom.repository.EmployeeRepository;
import com.example.wroom.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

/**
 * Implementation of Person Service
 *
 * @author Jonathan Rigottier
 */
@Service
@Transactional
public class PersonServiceImpl implements PersonService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void createPerson(Person person) {

    }

    @Override
    public Person findPersonById(UUID id) throws PersonNotFoundException {
        return null;
    }

    @Override
    public Person findPersonByEmail(String email) throws PersonNotFoundException {
        return null;
    }

    @Override
    public List<Person> findAllPersons() {
        return null;
    }

    @Override
    public void updatePerson(Person person) throws PersonNotFoundException {

    }

    @Override
    public void deletePersonById(UUID id) throws PersonNotFoundException {

    }

    @Override
    public void restorePersonById(UUID id) throws PersonNotFoundException {

    }
}
