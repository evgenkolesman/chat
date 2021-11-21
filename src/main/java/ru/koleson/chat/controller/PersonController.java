package ru.koleson.chat.controller;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.koleson.chat.model.Person;
import ru.koleson.chat.service.PersonService;

import java.util.List;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService service;

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
    @PutMapping("role/{id}")
}
