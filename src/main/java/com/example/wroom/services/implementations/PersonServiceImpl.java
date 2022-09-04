package com.example.wroom.services.implementations;

import com.example.wroom.exceptions.PersonNotFoundException;
import com.example.wroom.models.Person;
import com.example.wroom.repository.PersonRepository;
import com.example.wroom.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
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
    private PersonRepository personRepository;

    @Override
    public void createPerson(Person person) {
        person.setActive(true);
        personRepository.save(person);
    }

    @Override
    public Person findPersonById(UUID id) throws PersonNotFoundException {
        Optional<Person> optionalPerson = personRepository.findById(id);

        if(optionalPerson.isEmpty()) {
            throw new PersonNotFoundException(id);
        }
        return optionalPerson.get();
    }

    @Override
    public Person findPersonByEmail(String email) throws PersonNotFoundException {
        Optional<Person> optionalPerson = personRepository.findByEmail(email);

        if(optionalPerson.isEmpty()) {
            throw new PersonNotFoundException(email);
        }
        return optionalPerson.get();
    }

    @Override
    public List<Person> findAllPersons() {
        return personRepository.findAll();
    }

    @Override
    public void updatePerson(Person person) throws PersonNotFoundException {
        if(findPersonById(person.getId()) != null) {
            personRepository.saveAndFlush(person);
        }
    }

    @Override
    public void deletePersonById(UUID id) throws PersonNotFoundException {
        Person person = findPersonById(id);
        person.setActive(false);
        personRepository.saveAndFlush(person);
    }

    @Override
    public void restorePersonById(UUID id) throws PersonNotFoundException {
        Person person = findPersonById(id);
        person.setActive(true);
        personRepository.saveAndFlush(person);
    }
}
