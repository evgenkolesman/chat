package ru.koleson.chat.controller;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.koleson.chat.model.Role;
import ru.koleson.chat.service.RoleService;

import java.rmi.ServerException;
import java.util.List;

@RequestMapping("person/role/")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService service;

    @GetMapping
    public List<Role> findAll() {
    return service.findAll();
    }

    @GetMapping("/{name}")
    public Role findByName(@PathVariable String name) throws NotFoundException {
        return service.findByRole(name);
    }

    @PostMapping
    public String create(Role role) throws ServerException {
       return service.create(role);

    }

    @PutMapping
    public String update(Role role) throws Exception {
        return service.update(role);
    }

    @DeleteMapping
    public String delete(Role role) {
        return service.delete(role);
    }
}
