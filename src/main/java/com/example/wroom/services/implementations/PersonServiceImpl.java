package com.example.wroom.services.implementations;

import com.example.wroom.exceptions.PersonNotFoundException;
import com.example.wroom.models.Person;
import com.example.wroom.services.PersonService;

import java.util.List;
import java.util.UUID;

public class PersonServiceImpl implements PersonService {
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
