package ru.koleson.chat.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.koleson.chat.model.Person;
import ru.koleson.chat.service.PersonService;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final PersonService personService;
    private final BCryptPasswordEncoder encoder;


    @PostMapping("/sign-up")
    public void signUp(@RequestBody Person person) throws Exception {
        person.setPassword(encoder.encode(person.getPassword()));
        personService.createPerson(person);
    }

    @GetMapping("/all")
    public List<Person> findAll() {
        return personService.findAll();
    }
}
