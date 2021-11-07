package ru.koleson.chat.repository;

import org.springframework.data.repository.CrudRepository;
import ru.koleson.chat.model.Person;

public interface PersonRepository extends CrudRepository<Person, String> {

    Person findByLogin(String login);

}
