package ru.koleson.role.controller;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.koleson.role.model.Roles;
import ru.koleson.role.service.RoleService;

import java.rmi.ServerException;
import java.util.List;

@RequestMapping("person/role/")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService service;

    @GetMapping
    public List<Roles> findAll() {
    return service.findAll();
    }

    @GetMapping("/{name}")
    public Roles findByName(@PathVariable String name) throws NotFoundException {
        return service.findByRole(name);
    }

    @PostMapping
    public String create(Roles role) throws ServerException {
       return service.create(role);

    }

    @PutMapping
    public String update(Roles role) throws Exception {
        return service.update(role);
    }

    @DeleteMapping
    public String delete(Roles role) {
        return service.delete(role);
    }
}
