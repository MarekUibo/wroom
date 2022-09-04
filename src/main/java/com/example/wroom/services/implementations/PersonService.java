package com.example.wroom.services.implementations;

import com.example.wroom.exceptions.PersonNotFoundException;
import com.example.wroom.models.Person;

import java.util.List;
import java.util.UUID;

/**
 * To handle person related operations
 *
 * @author Rigottier Jonathan
 */
public interface PersonService {
    /**
     * To create a new person
     * @param person Person
     */
    void createPerson(Person person);

    /**
     * To find a person by its ID
     * @param id id of the person
     * @return Person
     */
    Person findPersonById(UUID id) throws PersonNotFoundException;

    /**
     * To find a person by its email
     * @param email email of the person
     * @return Person
     */
    Person findPersonByEmail(String email) throws PersonNotFoundException;

    /**
     * To find all persons
     * @return List of persons
     */
    List<Person> findAllPersons();

    /**
     * To update an existing person
     * @param person Person
     */
    void updatePerson(Person person) throws PersonNotFoundException;

    /**
     * To delete person by its ID
     * @param id id of the person
     */
    void deletePersonById(UUID id) throws PersonNotFoundException;

    /**
     * To restore a person by its ID
     * @param id
     */
    void restorePersonById(UUID id) throws PersonNotFoundException;
}
