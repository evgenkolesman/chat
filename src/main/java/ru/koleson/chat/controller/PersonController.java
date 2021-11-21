package ru.koleson.chat.controller;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.koleson.chat.model.Person;
import ru.koleson.chat.service.PersonService;

import java.util.List;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {

    private final RestTemplate rest;
    private final PersonService service;

    private String GETROLES = "http://localhost:8080/person/role";
    private String GETROLE = "http://localhost:8080/person/role/{name}";

    @GetMapping("/all")
    public List<Person> findAll() {
       return service.findAll();
    }

    @GetMapping("/{login}")
    public Person findByLogin (@PathVariable String login) throws NotFoundException {
        return service.findByLogin(login);
    }

    @PostMapping("/reg/{login}")
    public String create(@RequestBody Person person) throws Exception {
        service.createPerson(person);
        return "200";
    }

    @PutMapping("/{login}")
    public String updatePerson(@RequestBody Person person) throws Exception {
        service.updatePerson(person);
        return "200";
    }

    @DeleteMapping("/{login}")
    public String deletePerson(@PathVariable String login) throws Exception {
        service.deletePerson(login);
        return "200";
    }

    /**
     * we use restTeamplate for update Role of Person
     */
    @PutMapping("role/update/{id}/{name}")
    public String addRole(@PathVariable Long id, @PathVariable String name)  {
        Person.setRole(rest.put(GETROLE, name));
        return "";
    }
}
