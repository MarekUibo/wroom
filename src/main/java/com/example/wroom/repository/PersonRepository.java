package com.example.wroom.repository;
/**
 * @author:Marek Uibo
 */
import com.example.wroom.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}

